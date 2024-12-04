package uniandes.edu.co.proyecto.modelo;


import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorias")  // Nombre de la colección en MongoDB
public class Categoria {

    @Id
    private int id;
    private String descripcion;
    private String nombre;
    private String caracteristica_almacenamiento;
    private List<Producto> productos;

    // Constructor vacío
    public Categoria() {}

    // Constructor con parámetros
    public Categoria(int id, String descripcion, String nombre, String caracteristica_almacenamiento, List<Producto> productos) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.caracteristica_almacenamiento = caracteristica_almacenamiento;
        this.productos = productos;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicaAlmacenamiento() {
        return caracteristica_almacenamiento;
    }

    public void setCaracteristicaAlmacenamiento(String caracteristica_almacenamiento) {
        this.caracteristica_almacenamiento = caracteristica_almacenamiento;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", descripcion=" + descripcion + ", nombre=" + nombre + ", caracteristica_almacenamiento="
                + caracteristica_almacenamiento + ", productos=" + productos + "]";
    }
}



