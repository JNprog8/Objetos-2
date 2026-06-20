package ejercicio2.ui;

import ejercicio2.controller.InscripcionController;
import ejercicio2.model.Concurso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private InscripcionController controller;

    private JPanel contentPane;
    private JLabel lblName;
    private JTextField txtName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblId;
    private JTextField txtId;
    private JLabel lblPhone;
    private JTextField txtPhone;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JComboBox<Concurso> comboBox;
    private JButton btnOk;
    private JLabel lblCompetition;

    public VentanaPrincipal(InscripcionController controller) {
        this.controller = controller;
        var frame = new JFrame("Inscription to Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 451, 229);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        formElements();
        layout();
        frame.setVisible(true);
    }

    private void formElements() {
        lblName = new JLabel("Nombre:");
        txtName = new JTextField();
        txtName.setColumns(10);
        lblLastName = new JLabel("Apellido:");
        txtLastName = new JTextField();
        txtLastName.setColumns(10);
        lblId = new JLabel("Dni:");
        txtId = new JTextField();
        txtId.setColumns(10);
        lblPhone = new JLabel("Telefono:");
        txtPhone = new JTextField();
        txtPhone.setColumns(10);
        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        btnOk = new JButton("Ok");

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOk.setEnabled(false);
                try {
                    saveInscription(
                            txtId.getText(),
                            txtName.getText(),
                            txtLastName.getText(),
                            txtPhone.getText(),
                            txtEmail.getText(),
                            (Concurso) comboBox.getSelectedItem()
                    );
                    JOptionPane.showMessageDialog(contentPane, "Inscripcion registrada");
                    limpiarFormulario();
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    btnOk.setEnabled(true);
                }
            }
        });
        lblCompetition = new JLabel("Concurso:");
        comboBox = new JComboBox<>();
        todosLosConcursos();
    }

    private void todosLosConcursos() {
        comboBox.removeAllItems();
        for (Concurso concurso : controller.todosLosConcursos()) {
            comboBox.addItem(concurso);
        }
        btnOk.setEnabled(comboBox.getItemCount() > 0);
    }

    private void saveInscription(String dni, String nombre, String apellido, String telefono, String email, Concurso concurso) {
        controller.saveInscription(dni, nombre, apellido, telefono, email, concurso);
    }

    private void limpiarFormulario() {
        txtName.setText("");
        txtLastName.setText("");
        txtId.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }

    private void layout() {
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane
                .setHorizontalGroup(gl_contentPane
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane
                                                .createSequentialGroup()
                                                .addGroup(gl_contentPane
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblId)
                                                        .addComponent(lblLastName)
                                                        .addComponent(lblName)
                                                        .addComponent(lblPhone)
                                                        .addComponent(lblEmail)
                                                        .addComponent(lblCompetition))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                                .addGroup(gl_contentPane
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtEmail, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtPhone, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtId, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtLastName, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtName, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                                        .addComponent(btnOk, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        gl_contentPane
                .setVerticalGroup(gl_contentPane
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane
                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblLastName)
                                        .addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane
                                        .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblId)
                                        .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_contentPane
                                                .createSequentialGroup()
                                                .addComponent(lblPhone)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblEmail))
                                        .addGroup(gl_contentPane
                                                .createSequentialGroup()
                                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane
                                                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblCompetition))
                                        )
                                )
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOk)
                                .addContainerGap(67, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }
}
