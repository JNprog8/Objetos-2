package observer.punto5.model.tarjetas;

import observer.punto5.model.productos.Bebida;
import observer.punto5.model.productos.PlatoPrincipal;

public interface Tarjeta {
    double aplicarDescuentoA(Bebida bebida);

    double aplicarDescuentoA(PlatoPrincipal plato);
}
