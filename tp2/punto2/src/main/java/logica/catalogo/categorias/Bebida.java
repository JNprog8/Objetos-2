package logica.catalogo.categorias;

import logica.facturacion.tarjetas.TarjetaCredito;

public class Bebida implements Categoria {
    @Override
    public double calcularDescuento(TarjetaCredito tarjeta, double monto) {
        return tarjeta.descuentoSobreBebida(monto);
    }
}
