package main;

import logica.catalogo.Producto;
import logica.catalogo.categorias.PlatoPrincipal;
import logica.facturacion.propina.PorcentajePropina;
import logica.facturacion.tarjetas.Visa;
import logica.mesa.Mesa;
import logica.restaurante.Restaurante;
import persistence.JDBCRegistrarFactura;
import persistence.RegistrarFacturaEnArchivo;

import java.util.List;

//Ventas por Mesa
public class MainPunto2 {
    public static void main(String[] args) {
        String folder = "ventas_historicos";
        String dbUrl = "jdbc:sqlite:" + folder + "/ventas.db";

        Restaurante miResto = new Restaurante("Sabores Locales");

        // mesa 1 -> base de datos
        int num1 = 1;
        Mesa mesa1 = new Mesa(num1, 4);
        var jdbcMesa1 = new JDBCRegistrarFactura(dbUrl, num1);
        miResto.agregarMesa(mesa1, List.of(jdbcMesa1));

        // mesa 2 -> base de datos y archivo texto
        int num2 = 2;
        Mesa mesa2 = new Mesa(num2, 2);
        var jdbcMesa2 = new JDBCRegistrarFactura(dbUrl, num2);
        var txtMesa2 = new RegistrarFacturaEnArchivo(num2, folder);
        miResto.agregarMesa(mesa2, List.of(jdbcMesa2, txtMesa2));

        // flujo facturar una mesa
        mesa2.nuevoPedido();
        Producto pizza = new Producto("Pizza Muzzarella", 4500, new PlatoPrincipal());
        mesa2.agregarAlPedido(pizza, 1);

        System.out.println("Cerrando Mesa " + num2 + "...");
        mesa2.cerrarMesa(new Visa(), PorcentajePropina.CINCO_PORCIENTO);

        System.out.println("Datos guardados en: " + folder);
    }
}
