package catalogo.categoria;

import facturacion.tarjeta.TarjetaCredito;

public class Comida implements Categoria {
    @Override
    public double calcularDescuento(double subtotal, TarjetaCredito tarjeta) {
        return tarjeta.descuentoPlatoPrincipal(subtotal);
    }
}