package strategy.punto1.model.domain;

public class Producto {
    private final String nombre;
    private final float precio;
    private final float peso;

    public Producto(String nombre, float precio, float peso) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
    }

    public float precio() {
        return precio;
    }

    public float peso() {
        return peso;
    }

    public String nombre() {
        return nombre;
    }
}
