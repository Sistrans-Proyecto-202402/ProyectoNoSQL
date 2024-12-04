package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedores")  // Nombre de la colección en MongoDB
public class Proveedor {

    @Id
    private int id;
    private String nombre;
    private String direccion;
    private String nombre_contacto;
    private long telefono_contacto;

    // Constructor vacío
    public Proveedor() {}

    // Constructor con parámetros
    public Proveedor(int id, String nombre, String direccion, String nombre_contacto, long telefono_contacto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombre_contacto = nombre_contacto;
        this.telefono_contacto = telefono_contacto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreContacto() {
        return nombre_contacto;
    }

    public void setNombreContacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public long getTelefonoContacto() {
        return telefono_contacto;
    }

    public void setTelefonoContacto(long telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    @Override
    public String toString() {
        return "Proveedor [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", nombre_contacto="
                + nombre_contacto + ", telefono_contacto=" + telefono_contacto + "]";
    }
}
