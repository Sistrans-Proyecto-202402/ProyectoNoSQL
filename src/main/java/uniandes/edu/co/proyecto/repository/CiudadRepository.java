package uniandes.edu.co.proyecto.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends MongoRepository<Ciudad, Integer> {
    @Query(value="{}")
    List<Ciudad> buscarTodasLasCiudades();

}