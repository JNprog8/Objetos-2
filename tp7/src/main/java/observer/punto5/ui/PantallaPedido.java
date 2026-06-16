package observer.punto5.ui;

import observer.punto5.model.productos.Bebida;
import observer.punto5.model.productos.ItemMenu;
import observer.punto5.model.productos.PlatoPrincipal;
import observer.punto5.model.Mesa;
import observer.punto5.model.propinas.Propina;
import observer.punto5.model.propinas.PropinaFija;
import observer.punto5.model.tarjetas.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PantallaPedido extends JFrame {
    private Mesa mesa;
    private List<ItemMenu> menu;
    private JComboBox<String> cbItems;
    private JSpinner spCantidad;
    private JComboBox<String> cbTarjeta;
    private JComboBox<String> cbPropina;
    private DefaultListModel<String> listModel;

    public PantallaPedido(Mesa mesa) {
        this.mesa = mesa;
        this.menu = cargarMenu();

        setTitle("Mesa " + mesa.numero() + " - Realizar Pedido");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de Selección
        JPanel panelNorte = new JPanel(new GridLayout(3, 2, 5, 5));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Seleccionar Consumo"));

        cbItems = new JComboBox<>();
        for (ItemMenu item : menu) {
            cbItems.addItem(item.nombre() + " ($" + item.precio() + ")");
        }

        spCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

        JButton btnAgregar = new JButton("Agregar al Pedido");
        btnAgregar.addActionListener(e -> agregarItem());

        panelNorte.add(new JLabel("Producto:"));
        panelNorte.add(cbItems);
        panelNorte.add(new JLabel("Cantidad:"));
        panelNorte.add(spCantidad);
        panelNorte.add(new JLabel(""));
        panelNorte.add(btnAgregar);

        add(panelNorte, BorderLayout.NORTH);

        // Lista de Pedido
        listModel = new DefaultListModel<>();
        JList<String> listItems = new JList<>(listModel);
        add(new JScrollPane(listItems), BorderLayout.CENTER);

        // Panel de Pago
        JPanel panelSur = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSur.setBorder(BorderFactory.createTitledBorder("Finalizar y Pagar"));

        cbTarjeta = new JComboBox<>(new String[]{"Visa", "Mastercard", "Comarca Plus", "Otras (Viedma)"});
        cbPropina = new JComboBox<>(new String[]{"2%", "3%", "5%"});

        JButton btnPagar = new JButton("Confirmar Pago");
        btnPagar.addActionListener(e -> procesarPago());

        panelSur.add(new JLabel("Tarjeta:"));
        panelSur.add(cbTarjeta);
        panelSur.add(new JLabel("Propina:"));
        panelSur.add(cbPropina);
        panelSur.add(new JLabel(""));
        panelSur.add(btnPagar);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private List<ItemMenu> cargarMenu() {
        List<ItemMenu> items = new ArrayList<>();
        items.add(new PlatoPrincipal("Milanesa con Papas", 12000));
        items.add(new PlatoPrincipal("Parrillada Completa", 350000)); // Para probar el rojo
        items.add(new Bebida("Coca Cola", 2500));
        items.add(new Bebida("Vino Tinto", 8500));
        return items;
    }

    private void agregarItem() {
        int index = cbItems.getSelectedIndex();
        int cant = (int) spCantidad.getValue();
        ItemMenu item = menu.get(index);

        mesa.agregarItemAPedido(item, cant);
        listModel.addElement(cant + " x " + item.nombre() + " ($" + (item.precio() * cant) + ")");
    }

    private void procesarPago() {
        try {
            Tarjeta tarjeta;
            switch (cbTarjeta.getSelectedIndex()) {
                case 0: tarjeta = new Visa(); break;
                case 1: tarjeta = new Mastercard(); break;
                case 2: tarjeta = new ComarcaPlus(); break;
                default: tarjeta = new Viedma(); break;
            }

            Propina propina;
            switch (cbPropina.getSelectedIndex()) {
                case 0: propina = PropinaFija.dosPorciento(); break;
                case 1: propina = PropinaFija.tresPorciento(); break;
                default: propina = PropinaFija.cincoPorciento(); break;
            }

            mesa.pagar(tarjeta, propina);

            JOptionPane.showMessageDialog(this, "Pago realizado con éxito.");
            listModel.clear();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
