package resolucion.punto5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductosConPermisosTest {

    private List<Producto> productosIniciales;

    @BeforeEach
    void setUp() {
        this.productosIniciales = new ArrayList<>();
        this.productosIniciales.add(new Producto("p1"));
        this.productosIniciales.add(new Producto("p2"));
        this.productosIniciales.add(new Producto("p3"));
        this.productosIniciales.add(new Producto("p4"));
        this.productosIniciales.add(new Producto("p5"));
        this.productosIniciales.add(new Producto("p6"));
        this.productosIniciales.add(new Producto("p7"));
    }

    @Test
    void agregaProductosSinConocerLaSeguridad() {
        var productos = new Productos(new ArrayList<>(this.productosIniciales));

        productos.addProducto(new Producto("a1"));

        assertEquals(8, productos.cantidad());
        assertTrue(productos.contiene(new Producto("a1")));
    }

    @Test
    void remueveProductosSinConocerLaSeguridad() {
        var productos = new Productos(new ArrayList<>(this.productosIniciales));

        productos.removeProducto(new Producto("p6"));

        assertEquals(6, productos.cantidad());
        assertFalse(productos.contiene(new Producto("p6")));
    }

    @Test
    void permiteAgregarCuandoElUsuarioTienePermisos() {
        var productos = new ProductosConPermisos(
                new Productos(new ArrayList<>(this.productosIniciales)),
                new SecuritySubSystem()
        );

        productos.addProducto("1", new Producto("a1"));

        assertTrue(productos.listAll("1").contains(new Producto("a1")));
    }

    @Test
    void permiteListarCuandoElUsuarioTienePermisos() {
        var productos = new ProductosConPermisos(
                new Productos(new ArrayList<>(this.productosIniciales)),
                new SecuritySubSystem()
        );

        assertEquals(7, productos.listAll("1").size());
    }

    @Test
    void rechazaAgregarCuandoElUsuarioNoTienePermisos() {
        var productos = new ProductosConPermisos(
                new Productos(new ArrayList<>(this.productosIniciales)),
                new SecuritySubSystem()
        );

        assertThrows(RuntimeException.class, () -> productos.addProducto("2", new Producto("a1")));
    }

    @Test
    void rechazaRemoverCuandoElUsuarioNoTienePermisos() {
        var productos = new ProductosConPermisos(
                new Productos(new ArrayList<>(this.productosIniciales)),
                new SecuritySubSystem()
        );

        assertThrows(RuntimeException.class, () -> productos.removeProducto("2", new Producto("p1")));
    }

    @Test
    void rechazaListarCuandoElUsuarioNoTienePermisos() {
        var productos = new ProductosConPermisos(
                new Productos(new ArrayList<>(this.productosIniciales)),
                new SecuritySubSystem()
        );

        assertThrows(RuntimeException.class, () -> productos.listAll("2"));
    }
}
