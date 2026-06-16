package observer.punto5.model.productos;

import observer.punto5.model.tarjetas.Tarjeta;

public class Bebida extends ItemMenu {
    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public double calcularPrecioConDescuento(Tarjeta tarjeta) {
        return tarjeta.aplicarDescuentoA(this);
    }
}
