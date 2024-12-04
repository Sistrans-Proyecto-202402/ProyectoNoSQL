package uniandes.edu.co.proyecto.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Categoria;



public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {

    @Query(value="{}", fields="{ 'productos' : 0}")
    List<Categoria> buscarTodasLasCategorias();

    @Query("{ $insert: {_id: ?0, descripcion: ?1, nombre: ?2, caracteristica_almacenamiento: ?3, productos: [] } }")
    void insertarCategoria(int id, String descripcion, String nombre, String caracteristica_almacenamiento);

    @Query("{_id: ?0}")
    @Update("{ $push: { productos: { nombre: '?1', precio_unitario: ?2, presentacion: '?3', cantidad_en_presentacion: ?4, unidad_medida: '?5', volumen_empaque: ?6, peso_empaque: ?7, codigo_barras: '?8' } } }")
    void insertarProducto(int id, String nombre, double precio_unitario, String presentacion, double cantidad_en_presentacion, String unidad_medida, double volumen_empaque, double peso_empaque, String codigo_barras);

}
