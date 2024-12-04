package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import lombok.ToString;

@ToString
public class Bodega {

    private String nombre;
    private double tamanio;
    private double volumen;
    private double volumen_ocupado;
    private List<Almacenamiento> almacenamientos;

    // Constructor vacío
    public Bodega() {}

    // Constructor con parámetros
    public Bodega(String nombre, double tamanio, double volumen, double volumen_ocupado, List<Almacenamiento> almacenamientos) {
     
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.volumen = volumen;
        this.volumen_ocupado = volumen_ocupado;
        this.almacenamientos = almacenamientos;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getVolumenOcupado() {
        return volumen_ocupado;
    }

    public void setVolumenOcupado(double volumen_ocupado) {
        this.volumen_ocupado = volumen_ocupado;
    }

    public List<Almacenamiento> getAlmacenamientos() {
        return almacenamientos;
    }

    public void setAlmacenamientos(List<Almacenamiento> almacenamientos) {
        this.almacenamientos = almacenamientos;
    }
    
}
