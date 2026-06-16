package strategy.punto3.model.context;

import strategy.punto3.model.strategy.CalculadorDePrecios;

public class Producto {
    private double precio;
    private CalculadorDePrecios calculador;

    public Producto(double precio, CalculadorDePrecios calculador) {
        this.precio = precio;
        this.calculador = calculador;
    }

    public double precioFinal() {
        return calculador.calcularPrecioTotal(precio);
    }
}
