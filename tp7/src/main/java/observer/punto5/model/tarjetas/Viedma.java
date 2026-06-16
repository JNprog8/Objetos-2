package observer.punto5.model.tarjetas;

import observer.punto5.model.productos.Bebida;
import observer.punto5.model.productos.PlatoPrincipal;

public class Viedma implements Tarjeta {
    @Override
    public double aplicarDescuentoA(Bebida bebida) {
        return bebida.precio();
    }

    @Override
    public double aplicarDescuentoA(PlatoPrincipal plato) {
        return plato.precio();
    }
}
