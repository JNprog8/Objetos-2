package catalogo.categoria;

import facturacion.tarjeta.TarjetaCredito;

public interface Categoria {
    double calcularDescuento(double subtotal, TarjetaCredito tarjeta);
}