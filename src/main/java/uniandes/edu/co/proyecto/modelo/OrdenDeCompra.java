package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ordenes_de_compra")  // Nombre de la colección en MongoDB
public class OrdenDeCompra {

    @Id
    private int id;
    private Date fecha_creacion;
    private Date fecha_entrega;
    private Date fecha_estimada;
    private String estado;
    private DetalleProducto detalle_producto;
    private String bodega;
    private int sucursal;
    private int proveedor;

    // Constructor vacío
    public OrdenDeCompra() {}

    // Constructor con parámetros
    public OrdenDeCompra(int id, Date fecha_creacion, Date fecha_entrega, Date fecha_estimada, String estado, 
                         DetalleProducto detalle_producto, String bodega, int sucursal, int proveedor) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.fecha_entrega = fecha_entrega;
        this.fecha_estimada = fecha_estimada;
        this.estado = estado;
        this.detalle_producto = detalle_producto;
        this.bodega = bodega;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fecha_creacion;
    }

    public void setFechaCreacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFechaEntrega() {
        return fecha_entrega;
    }

    public void setFechaEntrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date getFechaEstimada() {
        return fecha_estimada;
    }

    public void setFechaEstimada(Date fecha_estimada) {
        this.fecha_estimada = fecha_estimada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DetalleProducto getDetalleProducto() {
        return detalle_producto;
    }

    public void setDetalleProducto(DetalleProducto detalle_producto) {
        this.detalle_producto = detalle_producto;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "OrdenDeCompra [id=" + id + ", fecha_creacion=" + fecha_creacion + ", fecha_entrega=" + fecha_entrega
                + ", fecha_estimada=" + fecha_estimada + ", estado=" + estado + ", detalle_producto=" + detalle_producto
                + ", bodega=" + bodega + ", sucursal=" + sucursal + ", proveedor=" + proveedor + "]";
    }
}

