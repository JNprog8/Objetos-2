package observer.punto5.main;

import observer.punto5.model.Mesa;
import observer.punto5.model.Observer;
import observer.punto5.persistence.RegistroArchivoVenta;
import observer.punto5.persistence.RegistroBaseDatosVenta;
import observer.punto5.ui.PantallaGerente;
import observer.punto5.ui.PantallaPedido;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilizando el ejercicio del restaurante del TP 1 y TP 2, implemente
 * utilizando el patrón Observer, una pantalla que tendrá el gerente general del restaurante con el
 * monto de la facturación de la última mesa. Cree una pantalla para seleccionar platos, bebidas
 * y permita pagar el total. Cada vez que se efectúa una venta, la pantalla del gerente reflejará el
 * monto facturado. Si el monto facturado supera los 300.000 pesos, el monto en la pantalla debe
 * aparecer de color rojo.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaGerente gerenteView = new PantallaGerente();
            Observer archivo = new RegistroArchivoVenta("src/main/java/observer/punto5/ventas_restaurante.txt");
            Observer bdd = new RegistroBaseDatosVenta();

            List<Mesa> mesas = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {

                int capacidad = (i % 2 == 0) ? 4 : 2;
                if (i == 10) capacidad = 8;

                Mesa mesa = new Mesa(i, capacidad);

                mesa.attach(gerenteView);
                mesa.attach(archivo);
                mesa.attach(bdd);

                mesas.add(mesa);
            }

            gerenteView.mostrar();

            PantallaPedido pedidoMesa1 = new PantallaPedido(mesas.get(0));
            pedidoMesa1.mostrar();

            // Opcional: tener una pantalla de selección de mesa primero
            // pero para cumplir el requerimiento de "cree una pantalla para seleccionar platos..."
            // mostramos directamente la de una mesa.
        });
    }
}
