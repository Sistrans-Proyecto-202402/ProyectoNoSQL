package uniandes.edu.co.proyecto.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;



public interface ProveedorRepository extends MongoRepository<Proveedor, Integer> {

    @Query(value="{}")
    List<Proveedor> buscarTodosLosProveedores();

    @Query("{ $insert: { _id: ?0, nombre: ?1, direccion: ?2, nombre_contacto: ?3, telefono_contacto: ?4} }")
    void insertarProveedor(int id, String nombre, String direccion, String nombre_contacto, long telefono_contacto);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, direccion: ?2, nombre_contacto: ?3, telefono_contacto: ?4 } }")
    void actualizarProveedor(int id, String nombre, String direccion, String nombre_contacto, long telefono_contacto);

}