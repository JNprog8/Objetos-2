package logica.catalogo;

import logica.catalogo.categorias.Categoria;
import logica.facturacion.tarjetas.TarjetaCredito;

public class Producto {
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Producto(String nombre, double precio, Categoria categoria) {
        validarNombre(nombre);
        validarPrecioPositivo(precio);
        validarCategoriaNoNula(categoria);
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
    }

    private static void validarPrecioPositivo(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
    }

    private static void validarCategoriaNoNula(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula.");
        }
    }

    public double precio() {
        return this.precio;
    }

    public double aplicarDescuento(TarjetaCredito tarjeta, double monto) {
        return this.categoria.calcularDescuento(tarjeta, monto);
    }
}
