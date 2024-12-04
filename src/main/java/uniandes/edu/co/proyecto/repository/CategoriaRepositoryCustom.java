package uniandes.edu.co.proyecto.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.Document;

@Repository
public class CategoriaRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public CategoriaRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Obtiene todos los productos de todas las categorías.
     *
     * @return Lista de productos de todas las categorías.
     */
    public List<Document> obtenerProductosDeTodasLasCategorias() {
        List<Document> pipeline = List.of(

            new Document("$unwind", "$productos"),
            new Document("$replaceRoot", new Document("newRoot", "$productos"))
        );
        return mongoTemplate.getCollection("categorias").aggregate(pipeline).into(new java.util.ArrayList<>());
    }

    public List<Document> clasificarProductos(int idSucursal, int idCategoria, double rangoPrecioMin, double rangoPrecioMax, Date fechaVencimiento) {
        List<Document> pipeline = List.of(
            // Filtrar por sucursal específica
            new Document("$match", new Document("_id", idSucursal)),
    
            // Desestructurar las bodegas
            new Document("$unwind", "$bodegas"),
    
            // Desestructurar los almacenamientos
            new Document("$unwind", "$bodegas.almacenamientos"),
    
            // Hacer un lookup para cruzar con la colección categorias usando el código de barras
            new Document("$lookup", new Document("from", "categorias")
                .append("let", new Document("codigoBarras", "$bodegas.almacenamientos.producto"))
                .append("pipeline", List.of(
                    new Document("$unwind", "$productos"),
                    new Document("$match", new Document("$expr",
                        new Document("$eq", List.of("$productos.codigo_barras", "$$codigoBarras"))
                    ))
                ))
                .append("as", "categoria_info")
            ),
    
            // Filtrar resultados según los parámetros
            new Document("$match", new Document()
                .append("categoria_info._id", idCategoria)
                .append("bodegas.almacenamientos.costo_promedio",
                    new Document("$gte", rangoPrecioMin).append("$lte", rangoPrecioMax))
                .append("bodegas.almacenamientos.fecha_expiracion",
                    new Document("$lt", fechaVencimiento))
            ),
    
            // Proyectar únicamente los productos
            new Document("$project", new Document()
                .append("producto", new Document("$arrayElemAt", List.of("$categoria_info.productos", 0)))
            )
        );
    
        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new ArrayList<>());
    }
    

    
    
    
    
}
