package punto4;

import java.util.ArrayList;
import java.util.List;

public class OrdenDeCompra {

    private EstadoOrden estado;
    private List<Producto> productos;
    private String numeroSeguimiento;

    public OrdenDeCompra() {
        this.productos = new ArrayList<>();
        this.estado = new EstadoIniciada(this);
        this.numeroSeguimiento = null;
    }

    public void agregarProducto(Producto producto) {
        this.estado.agregarProducto(producto);
    }

    protected void agregarProductoALista(Producto producto) {
        this.productos.add(producto);
    }

    public void confirmarCompra() {
        this.estado.enviar();
        this.numeroSeguimiento = generarNumeroSeguimiento();
    }

    public void cancelar() {
        this.estado.cancelar();
    }

    public EstadoOrden estado() {
        return this.estado;
    }

    public void nuevoEstado(EstadoOrden nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public double monto() {
        return this.calcularMonto();
    }

    public String numeroSeguimiento() {
        return this.numeroSeguimiento;
    }

    public List<Producto> productos() {
        return new ArrayList<>(this.productos);
    }

    private double calcularMonto() {
        return this.productos.stream().mapToDouble(Producto::precio).sum();
    }

    private String generarNumeroSeguimiento() {
        return "SEG-" + System.currentTimeMillis();
    }
}
