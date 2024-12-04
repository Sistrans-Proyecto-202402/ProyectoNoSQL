package uniandes.edu.co.proyecto.modelo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ciudades")  // Nombre de la colección en MongoDB
public class Ciudad {

    @Id
    private int id;
    private String nombre;
    private String departamento;

    // Constructor vacío
    public Ciudad() {}

    // Constructor con parámetros
    public Ciudad(int id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Ciudad [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + "]";
    }
}
