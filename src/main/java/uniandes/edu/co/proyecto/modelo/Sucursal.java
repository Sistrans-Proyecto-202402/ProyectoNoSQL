package uniandes.edu.co.proyecto.modelo;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sucursales")
public class Sucursal {

    @Id
    private int id;

    private String nombre;

    @Field("tamanio_instalacion") // Mapeo explícito del nombre en MongoDB
    private double tamanio_instalacion;

    private String direccion;

    private long telefono;

    @Field("ciudad_id") // Mapeo explícito del nombre en MongoDB
    private int ciudad_id;

    private List<Bodega> bodegas;

    // Constructor vacío
    public Sucursal() {}

    // Constructor con parámetros
    public Sucursal(int id, String nombre, double tamanio_instalacion, String direccion, long telefono, int ciudad_id, List<Bodega> bodegas) {
        this.id = id;
        this.nombre = nombre;
        this.tamanio_instalacion = tamanio_instalacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad_id = ciudad_id;
        this.bodegas = bodegas;
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

    public double getTamanioInstalacion() {
        return tamanio_instalacion;
    }

    public void setTamanioInstalacion(double tamanio_instalacion) {
        this.tamanio_instalacion = tamanio_instalacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getCiudadId() {
        return ciudad_id;
    }

    public void setCiudadId(int ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    @Override
    public String toString() {
        return "Sucursal [id=" + id + ", nombre=" + nombre + ", tamanio_instalacion=" + tamanio_instalacion + ", direccion=" + direccion
                + ", telefono=" + telefono + ", ciudad_id=" + ciudad_id + ", bodegas=" + bodegas + "]";
    }
}


// Clase interna para Almacenamiento
class Almacenamiento {
    private double costo_promedio;
    private int cantidad;
    private int nivel_minimo_reorden;
    private String fecha_expiracion;
    private double costo_bodega;
    private String producto;

    // Constructor vacío
    public Almacenamiento() {}

    // Constructor con parámetros
    public Almacenamiento(double costo_promedio, int cantidad, int nivel_minimo_reorden, String fecha_expiracion, double costo_bodega, String producto) {
        this.costo_promedio = costo_promedio;
        this.cantidad = cantidad;
        this.nivel_minimo_reorden = nivel_minimo_reorden;
        this.fecha_expiracion = fecha_expiracion;
        this.costo_bodega = costo_bodega;
        this.producto = producto;
    }

    // Getters y Setters
    public double getCostoPromedio() {
        return costo_promedio;
    }

    public void setCostoPromedio(double costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNivelMinimoReorden() {
        return nivel_minimo_reorden;
    }

    public void setNivelMinimoReorden(int nivel_minimo_reorden) {
        this.nivel_minimo_reorden = nivel_minimo_reorden;
    }

    public String getFechaExpiracion() {
        return fecha_expiracion;
    }

    public void setFechaExpiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public double getCostoBodega() {
        return costo_bodega;
    }

    public void setCostoBodega(double costo_bodega) {
        this.costo_bodega = costo_bodega;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Almacenamiento [costo_promedio=" + costo_promedio + ", cantidad=" + cantidad + ", nivel_minimo_reorden=" + nivel_minimo_reorden
                + ", fecha_expiracion=" + fecha_expiracion + ", costo_bodega=" + costo_bodega + ", producto=" + producto + "]";
    }
}
