package oop2Enrique.lambdas.permissions2;

import java.util.Collections;
import java.util.List;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    public void addProducto(String userId, Producto producto) {
        if (!this.security.checkAddPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.add(producto);
    }

    public void removeProducto(String userId, Producto producto) {
        if (!this.security.checkRemovePermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        this.productos.remove(producto);
    }

    public List<Producto> listAll(String userId) {
        if (!this.security.checkListPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return Collections.unmodifiableList(this.productos);
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
