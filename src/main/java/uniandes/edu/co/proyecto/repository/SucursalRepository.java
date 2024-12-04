package uniandes.edu.co.proyecto.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends MongoRepository<Sucursal, Integer> {


    @Query(value="{}", fields = "{ 'bodegas.almacenamientos': 0 }")
    List<Sucursal> buscarTodasLasSucursales();

    @Query("{ $insert: { _id: ?0, nombre: ?1, tamanio_instalacion: ?2, direccion: ?3, telefono: ?4, ciudad_id: ?5, bodegas: [] } }")
    void insertarSucursal(int id, String nombre, double tamanio_instalacion, String direccion, long telefono, int ciudad_id);

    @Query("{_id: ?0}")
    @Update("{ $push: { bodegas: { nombre: '?1', tamanio: ?2, volumen: ?3, volumen_ocupado: ?4, almacenamientos:[] } } }")
    void insertarBodega(int id, String nombre, Double tamanio, Double volumen, Double volumen_ocupado);
    
    @Query("{_id: ?0}")
    @Update("{ $pull: { bodegas: { nombre: '?1' } } }")
    void eliminarBodega(int id, String nombre);


}


