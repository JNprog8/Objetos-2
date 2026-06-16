package observer.punto5.model.productos;

import observer.punto5.model.tarjetas.Tarjeta;

public class PlatoPrincipal extends ItemMenu {
    public PlatoPrincipal(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public double calcularPrecioConDescuento(Tarjeta tarjeta) {
        return tarjeta.aplicarDescuentoA(this);
    }
}
