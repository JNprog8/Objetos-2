package observer.punto4.ui;

import observer.punto4.controller.ParticipanteController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final JTextField txtNombre = createTextField();
    private final JTextField txtTelefono = createTextField();
    private final JTextField txtRegion = createTextField("China");
    private final JTextField txtEmail = createTextField();
    private final ParticipanteController controller;

    public VentanaPrincipal(ParticipanteController controller) {
        this.controller = controller;
        applySystemLookAndFeel();
        initComponents();
    }

    private void applySystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    private void initComponents() {
        setTitle("Sistema de Inscripción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 350));
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel formPanel = createFormPanel();
        JButton btnRegister = createRegisterButton();

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(btnRegister, BorderLayout.SOUTH);

        add(contentPanel);
        pack();
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new CompoundBorder(
                new TitledBorder(" Información del Participante "),
                new EmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        addLabeledField(panel, "Nombre Completo:", txtNombre, gbc, 0);
        addLabeledField(panel, "Teléfono Contacto:", txtTelefono, gbc, 1);
        addLabeledField(panel, "Región / Sede:", txtRegion, gbc, 2);
        addLabeledField(panel, "Correo Electrónico:", txtEmail, gbc, 3);

        return panel;
    }

    private void addLabeledField(JPanel panel, String label, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    private JButton createRegisterButton() {
        JButton btn = new JButton("Confirmar Inscripción");
        btn.setPreferredSize(new Dimension(0, 40));
        btn.setFont(btn.getFont().deriveFont(Font.BOLD, 14f));
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> handleRegistration());
        return btn;
    }

    private JTextField createTextField() { return createTextField(""); }
    private JTextField createTextField(String initialValue) {
        JTextField tf = new JTextField(initialValue, 20);
        tf.setMargin(new Insets(2, 5, 2, 5));
        return tf;
    }

    private void handleRegistration() {
        String n = txtNombre.getText().trim();
        String t = txtTelefono.getText().trim();
        String r = txtRegion.getText().trim();
        String e = txtEmail.getText().trim();

        if (n.isEmpty() || t.isEmpty() || r.isEmpty() || e.isEmpty()) {
            showWarning("Todos los campos son obligatorios.");
            return;
        }

        try {
            controller.registrarParticipante(n, t, r, e);
            JOptionPane.showMessageDialog(this, "Registro completado con éxito.", "Inscripción", JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void resetForm() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtRegion.setText("China");
        txtEmail.setText("");
        txtNombre.requestFocus();
    }

    private void showWarning(String msg) { JOptionPane.showMessageDialog(this, msg, "Atención", JOptionPane.WARNING_MESSAGE); }
    private void showError(String msg) { JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE); }

    public void mostrar() { setVisible(true); }
}
