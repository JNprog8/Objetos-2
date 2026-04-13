package logica.catalogo.categorias;

import logica.facturacion.tarjetas.TarjetaCredito;

public interface Categoria {
    double calcularDescuento(TarjetaCredito tarjeta, double monto);
}
