package resolucion.punto6;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Main {
    private static final String USUARIO_ADMIN = "1";
    private static final String USUARIO_INVITADO = "2";

    public static void main(String[] args) throws IOException {
        var productos = setup();
        ejercitacion(productos);
    }

    private static Productos setup() {
        System.out.println("== Setup ==");

        List<Producto> catalogoInicial = new ArrayList<>();
        catalogoInicial.add(new Producto("Yerba mate"));
        catalogoInicial.add(new Producto("Cafe en granos"));
        catalogoInicial.add(new Producto("Azucar mascabo"));
        catalogoInicial.add(new Producto("Te verde"));

        var seguridad = new SecuritySubSystem();
        var productos = new Productos(catalogoInicial, seguridad);

        System.out.println("Catalogo inicial: " + productos.listAll(USUARIO_ADMIN));
        return productos;
    }

    private static void ejercitacion(Productos productos) {
        System.out.println();
        System.out.println("== Ejercitacion ==");

        agregarProductoComoAdmin(productos);
        removerProductoComoAdmin(productos);
        intentarOperacionSinPermisos(
                "Agregar producto como invitado",
                () -> productos.addProducto(USUARIO_INVITADO, new Producto("Galletitas de avena"))
        );
        intentarOperacionSinPermisos(
                "Listar catalogo como invitado",
                () -> productos.listAll(USUARIO_INVITADO)
        );
    }

    private static void agregarProductoComoAdmin(Productos productos) {
        var productoNuevo = new Producto("Miel organica");
        productos.addProducto(USUARIO_ADMIN, productoNuevo);

        System.out.println("Admin agrega producto: " + productoNuevo.name());
        System.out.println("Catalogo actualizado: " + productos.listAll(USUARIO_ADMIN));
    }

    private static void removerProductoComoAdmin(Productos productos) {
        var productoARemover = new Producto("Azucar mascabo");
        productos.removeProducto(USUARIO_ADMIN, productoARemover);

        System.out.println("Admin remueve producto: " + productoARemover.name());
        System.out.println("Catalogo actualizado: " + productos.listAll(USUARIO_ADMIN));
    }

    private static void intentarOperacionSinPermisos(String descripcion, Accion accion) {
        try {
            accion.ejecutar();
        } catch (RuntimeException error) {
            System.out.println(descripcion + " -> " + error.getMessage());
        }
    }
}
