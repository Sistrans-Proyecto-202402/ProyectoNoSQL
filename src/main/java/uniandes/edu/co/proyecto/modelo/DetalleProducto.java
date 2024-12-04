package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

public class DetalleProducto {
    
    private int cantidad;
    private double precio;
    private Date fecha_vencimiento;
    private String producto;

    // Constructor vacío
    public DetalleProducto() {}

    // Constructor con parámetros
    public DetalleProducto(int cantidad, double precio, Date fecha_vencimiento, String producto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_vencimiento = fecha_vencimiento;
        this.producto = producto;
    }

    // Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaVencimiento() {
        return fecha_vencimiento;
    }

    public void setFechaVencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleProducto [cantidad=" + cantidad + ", precio=" + precio + ", fecha_vencimiento=" + fecha_vencimiento
                + ", producto=" + producto + "]";
    }
    
}
