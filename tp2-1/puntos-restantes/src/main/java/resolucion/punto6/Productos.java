package resolucion.punto6;

import java.util.Collections;
import java.util.List;

public class Productos {
    private final List<Producto> productos;
    private final SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.productos = productos;
        this.security = security;
    }

    public void addProducto(String userId, Producto producto) {
        this.security.ejecutarConPermiso(userId, () -> this.productos.add(producto));
    }

    public void removeProducto(String userId, Producto producto) {
        this.security.ejecutarConPermiso(userId, () -> this.productos.remove(producto));
    }

    public List<Producto> listAll(String userId) {
        return this.security.ejecutarConPermiso(
                userId,
                () -> Collections.unmodifiableList(this.productos)
        );
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
