package ui;


import modelo.Concurso;
import modelo.ExcepcionAplicacion;
import servicios.ServicioInscripcion;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class RadioCompetition extends JFrame {
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

    private ServicioInscripcion servicioInscripcion;

    public RadioCompetition(ServicioInscripcion servicioInscripcion) {
        System.out.println("DEBUG: Entrando al constructor de RadioCompetition.");
        this.servicioInscripcion = servicioInscripcion;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 229);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(new FlowLayout());

        setContentPane(contentPane);
        formElements();

        contentPane.add(lblName);
        contentPane.add(txtName);
        contentPane.add(lblLastName);
        contentPane.add(txtLastName);
        contentPane.add(lblId);
        contentPane.add(txtId);
        contentPane.add(lblPhone);
        contentPane.add(txtPhone);
        contentPane.add(lblEmail);
        contentPane.add(txtEmail);
        contentPane.add(lblCompetition);
        contentPane.add(comboBox);
        contentPane.add(btnOk);

        setVisible(true);
        System.out.println("DEBUG: Saliendo del constructor de RadioCompetition. Ventana visible.");
    }

    private void formElements() {
        System.out.println("DEBUG: Entrando a formElements()."); // DEBUG LINE
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
                    saveInscription();
                    JOptionPane.showMessageDialog(contentPane, "Inscripción guardada con éxito.");
                    clearForm();
                } catch (ExcepcionAplicacion ex) {
                    JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error de Validación", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    btnOk.setEnabled(true);
                }
            }
        });
        lblCompetition = new JLabel("Concurso:");
        comboBox = new JComboBox<>();
        todosLosConcursos();
        System.out.println("DEBUG: Saliendo de formElements()."); // DEBUG LINE
    }

    private void todosLosConcursos() {
        System.out.println("DEBUG: Entrando a todosLosConcursos()."); // DEBUG LINE
        try {
            List<Concurso> concursos = servicioInscripcion.todosLosConcursos(LocalDate.now());
            comboBox.removeAllItems();
            comboBox.addItem(null); // Opción por defecto "Seleccionar..."
            for (Concurso c : concursos) {
                comboBox.addItem(c);
            }
            System.out.println("DEBUG: Concursos cargados: " + concursos.size()); // DEBUG LINE
        } catch (ExcepcionAplicacion e) {
            System.err.println("ERROR: ExcepcionAplicacion en todosLosConcursos(): " + e.getMessage()); // DEBUG ERROR
            e.printStackTrace(); // PRINT STACK TRACE
            JOptionPane.showMessageDialog(this.contentPane, "Error al cargar concursos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("ERROR: Error inesperado en todosLosConcursos(): " + e.getMessage()); // DEBUG ERROR
            e.printStackTrace(); // PRINT STACK TRACE
            JOptionPane.showMessageDialog(this.contentPane, "Error inesperado al cargar concursos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("DEBUG: Saliendo de todosLosConcursos()."); // DEBUG LINE
    }

    private void saveInscription() {
        Concurso concursoSeleccionado = (Concurso) comboBox.getSelectedItem();
        if (concursoSeleccionado == null) {
            throw new ExcepcionAplicacion("Debe elegir un Concurso.");
        }

        servicioInscripcion.saveInscription(
                txtLastName.getText(),
                txtName.getText(),
                txtId.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                concursoSeleccionado
        );
    }

    private void clearForm() {
        txtName.setText("");
        txtLastName.setText("");
        txtId.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        comboBox.setSelectedItem(null);
    }

    public void layout() {
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane
                .createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addGroup(gl_contentPane
                                .createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                                        .createSequentialGroup()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblLastName).addComponent(lblId)
                                                .addComponent(lblPhone).addComponent(lblEmail)
                                                .addComponent(lblName).addComponent(lblCompetition))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addGroup(
                                                gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(txtEmail, Alignment.TRAILING)
                                                        .addComponent(txtPhone, Alignment.TRAILING)
                                                        .addComponent(txtId, Alignment.TRAILING)
                                                        .addComponent(txtLastName, Alignment.TRAILING)
                                                        .addComponent(txtName, Alignment.TRAILING,
                                                                GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                                .addComponent(btnOk, Alignment.TRAILING,
                                        GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap()));
        gl_contentPane
                .setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(txtName, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblLastName).addComponent(txtLastName,
                                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblId).addComponent(
                                                txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(
                                                gl_contentPane.createSequentialGroup().addComponent(lblPhone)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblEmail))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
                                                        gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblCompetition))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnOk)
                                .addContainerGap(67, Short.MAX_VALUE)));
        contentPane.setLayout(gl_contentPane);
    }
}