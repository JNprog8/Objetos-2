package catalogo.categoria;

import facturacion.tarjeta.TarjetaCredito;

public class Bebida implements Categoria {
    @Override
    public double calcularDescuento(double subtotal, TarjetaCredito tarjeta) {
        return tarjeta.descuentoBebida(subtotal);
    }
}