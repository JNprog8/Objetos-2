package observer.punto5.ui;

import observer.punto5.model.Observer;

import javax.swing.*;
import java.awt.*;

public class PantallaGerente extends JFrame implements Observer {
    private JLabel lblMonto;

    public PantallaGerente() {
        setTitle("Panel del Gerente General");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lblMonto = new JLabel("Esperando facturación...", SwingConstants.CENTER);
        lblMonto.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblMonto, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public void mostrar() {
        setVisible(true);
    }

    @Override
    public void update(double monto) {
        lblMonto.setText(String.format("Última Venta: $%.2f", monto));
        if (monto > 300000) {
            lblMonto.setForeground(Color.RED);
        } else {
            lblMonto.setForeground(Color.BLACK);
        }
    }
}
