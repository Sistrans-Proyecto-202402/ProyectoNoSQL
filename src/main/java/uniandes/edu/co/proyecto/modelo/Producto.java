package uniandes.edu.co.proyecto.modelo;

import lombok.ToString;

@ToString
public class Producto {
    private String nombre;
    private double precio_unitario;
    private String presentacion;
    private double cantidad_en_presentacion;
    private String unidad_medida;
    private double volumen_empaque;
    private double peso_empaque;
    private String codigo_barras;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(String nombre, double precio_unitario, String presentacion, double cantidad_en_presentacion, String unidad_medida, double volumen_empaque, double peso_empaque, String codigo_barras) {
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
        this.presentacion = presentacion;
        this.cantidad_en_presentacion = cantidad_en_presentacion;
        this.unidad_medida = unidad_medida;
        this.volumen_empaque = volumen_empaque;
        this.peso_empaque = peso_empaque;
        this.codigo_barras = codigo_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public double getCantidad_en_presentacion() {
        return cantidad_en_presentacion;
    }

    public void setCantidad_en_presentacion(double cantidad_en_presentacion) {
        this.cantidad_en_presentacion = cantidad_en_presentacion;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public double getVolumen_empaque() {
        return volumen_empaque;
    }

    public void setVolumen_empaque(double volumen_empaque) {
        this.volumen_empaque = volumen_empaque;
    }

    public double getPeso_empaque() {
        return peso_empaque;
    }

    public void setPeso_empaque(double peso_empaque) {
        this.peso_empaque = peso_empaque;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }



    
}
