package repo.enrique.ui;

import repo.enrique.model.EstacionDeServicio;
import repo.enrique.model.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaVentasView {
    private final EstacionDeServicio estacion;

    public ListaVentasView(EstacionDeServicio estacion) {
        this.estacion = estacion;
    }

    public void createAndShowUI() {
        JFrame frame = new JFrame("Lista de Ventas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fecha de Venta");
        model.addColumn("Litros Cargados");
        model.addColumn("Combustible");
        model.addColumn("Monto Total");
        for (Venta venta : this.estacion.listarVentas()) {
            model.addRow(new Object[]{venta.fechaVenta(), venta.litrosCargados(), venta.tipoCombustible(), venta.montoTotal()});
        }
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

