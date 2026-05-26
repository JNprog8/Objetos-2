package punto6;

public abstract class Producto {
    private static final int DESCUENTO_POR_ENVIO_GRATIS = 10;
    public double precio;

    public Producto(double precio) {
        this.precio = precio;
    }

    public double precioFinal() {
        double total = precio * (1 + aplicarImpuesto()) * (1 - aplicarDescuento());
        if (tieneEnvioGratis()) {
            total -= DESCUENTO_POR_ENVIO_GRATIS;
        }
        return Math.round(total * 100) / 100.0;
    }

    protected abstract double aplicarDescuento();

    protected abstract double aplicarImpuesto();

    protected abstract boolean tieneEnvioGratis();
}