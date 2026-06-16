package strategy.punto1.model.context;

import strategy.punto1.model.domain.Producto;
import strategy.punto1.model.domain.destino.Destino;
import strategy.punto1.model.strategy.Enviar;

import java.util.ArrayList;
import java.util.List;

public class CarritoDeCompras {
    private final List<Producto> productos;

    public CarritoDeCompras() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public float calcularCostoTotal(Enviar strategy, Destino zona) {
        float totalProductos = (float) productos.stream()
                .mapToDouble(Producto::precio)
                .sum();

        float pesoTotal = (float) productos.stream()
                .mapToDouble(Producto::peso)
                .sum();

        // con DD cadaa zona sabe que metodo del strategy invocar
        return totalProductos + zona.calcularCostoCon(strategy, pesoTotal);
    }
}
