package resolucion.punto5;

import java.util.List;

public class ProductosConPermisos {
    private Productos productos;
    private SecuritySubSystem security;

    public ProductosConPermisos(Productos productos, SecuritySubSystem security) {
        this.productos = productos;
        this.security = security;
    }

    public void addProducto(String userId, Producto producto) {
        this.security.ejecutarConPermiso(userId, () -> this.productos.addProducto(producto));
    }

    public void removeProducto(String userId, Producto producto) {
        this.security.ejecutarConPermiso(userId, () -> this.productos.removeProducto(producto));
    }

    public List<Producto> listAll(String userId) {
        return this.security.ejecutarConPermiso(userId, this.productos::listAll);
    }
}
