package resolucion.punto5;

import java.util.Collections;
import java.util.List;

public class Productos {
    private List<Producto> productos;

    public Productos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto) {
        this.productos.remove(producto);
    }

    public List<Producto> listAll() {
        return Collections.unmodifiableList(this.productos);
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
