package observer.punto5.model.productos;

import observer.punto5.model.tarjetas.Tarjeta;

public abstract class ItemMenu {
    private String nombre;
    private double precio;

    protected ItemMenu(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public abstract double calcularPrecioConDescuento(Tarjeta tarjeta);

    public double precio() {
        return precio;
    }

    public String nombre() {
        return nombre;
    }
}
