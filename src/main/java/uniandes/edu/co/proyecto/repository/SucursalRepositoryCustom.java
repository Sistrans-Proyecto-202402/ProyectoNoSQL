package uniandes.edu.co.proyecto.repository;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.bson.Document;

@Repository
public class SucursalRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public SucursalRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Obtiene el INVENTARIO DE PRODUCTOS EN UNA SUCURSAL.
     *
     * @param idSucursal Identificador de la sucursal.
     * 
     * @return Lista de productos en la sucursal.
     */
    public List<Document> obtenerInventarioProductosPorSucursal(int idSucursal) {
        List<Document> pipeline = List.of(
            new Document("$match", new Document("_id", idSucursal)),
            new Document("$unwind", "$bodegas"),
            new Document("$unwind", "$bodegas.almacenamientos"),
            new Document("$lookup", new Document("from", "categorias")
                .append("let", new Document("productCode", "$bodegas.almacenamientos.producto"))
                .append("pipeline", List.of(
                    new Document("$unwind", "$productos"),
                    new Document("$match", new Document("$expr", 
                        new Document("$eq", List.of("$productos.codigo_barras", "$$productCode"))
                    )),
                    new Document("$project", new Document("nombre_producto", "$productos.nombre").append("_id", 0))
                ))
                .append("as", "productDetails")
            ),
            new Document("$unwind", "$productDetails"),
            new Document("$project", new Document()
                .append("nombre_bodega", "$bodegas.nombre")
                .append("nombre_producto", "$productDetails.nombre_producto")
                .append("cantidad_disponible", "$bodegas.almacenamientos.cantidad")
                .append("nivel_minimo_reorden", "$bodegas.almacenamientos.nivel_minimo_reorden")
                .append("costo_promedio", "$bodegas.almacenamientos.costo_promedio")
                .append("_id", 0)
            )
        );

        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new java.util.ArrayList<>());
    }


    /**
     * Obtiene todos los productos de todas las categorías.
     *
     * @return Lista de productos de todas las categorías.
     */
    public List<Document> obtenerBodegas() {
        List<Document> pipeline = List.of(
            // Descomponer el array "bodegas" en documentos individuales
            new Document("$unwind", "$bodegas"),
            
            // Reemplazar el documento raíz por "bodegas"
            new Document("$replaceRoot", new Document("newRoot", "$bodegas")),
            
            // Proyectar los campos deseados y excluir "almacenamientos"
            new Document("$project", new Document()
                .append("_id", 0) // Si no necesitas el ID original, puedes excluirlo también
            )
        );
    
        return mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new java.util.ArrayList<>());
    }


    /**
     * Obtiene el ID de la sucursal que contiene una bodega con un nombre específico.
     *
     * @param nombreBodega Nombre de la bodega.
     * @return El ID de la sucursal que contiene la bodega o null si no se encuentra.
     */
    public Integer obtenerIdSucursalPorBodega(String nombreBodega) {
        List<Document> pipeline = List.of(
            new Document("$unwind", "$bodegas"), // Descomponer el array "bodegas"
            new Document("$match", new Document("bodegas.nombre", nombreBodega)), // Filtrar por el nombre de la bodega
            new Document("$project", new Document("_id", 1)) // Proyectar únicamente el campo "_id"
        );

        // Ejecutar la consulta de agregación
        List<Document> resultado = mongoTemplate.getCollection("sucursales").aggregate(pipeline).into(new java.util.ArrayList<>());

        // Retornar el ID como Integer, si existe un resultado
        if (!resultado.isEmpty()) {
            return resultado.get(0).getInteger("_id"); // Obtener el ID como Integer
        }
        return null; // Retornar null si no se encuentra la bodega
    }
    
}
