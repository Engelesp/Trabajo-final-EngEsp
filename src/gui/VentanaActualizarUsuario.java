package gui;

import javax.swing.*;
import Modelos.Usuario;
import Modelos.UsuarioDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaActualizarUsuario extends JFrame {
    private JTextField txtUserName;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnActualizar;
    private JButton btnCancelar;

    private Usuario usuario;

    public VentanaActualizarUsuario(Usuario usuario) {
        this.usuario = usuario;
            System.out.println("ID del usuario cargado: " + usuario.getId()); // Imprime el ID
        
        // Configuración de la ventana
        setTitle("Actualizar Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Inicializar los componentes
        JLabel lblUserName = new JLabel("Nombre de Usuario:");
        txtUserName = new JTextField(usuario.getNombreUsuario());
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(usuario.getNombre());
        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField(usuario.getApellido());
        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(usuario.getTelefono());
        JLabel lblEmail = new JLabel("Correo:");
        txtEmail = new JTextField(usuario.getCorreo());
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(usuario.getContraseña());

        btnActualizar = new JButton("Actualizar");
        btnCancelar = new JButton("Cancelar");

        // Agregar los componentes al JFrame
        add(lblUserName);
        add(txtUserName);
        add(lblNombre);
        add(txtNombre);
        add(lblApellido);
        add(txtApellido);
        add(lblTelefono);
        add(txtTelefono);
        add(lblEmail);
        add(txtEmail);
        add(lblPassword);
        add(txtPassword);
        add(btnActualizar);
        add(btnCancelar);

        // Acción para actualizar el usuario
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        // Acción para cancelar la operación
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana de actualización
            }
        });
    }

    // Método para actualizar el usuario en la base de datos
    private void actualizarUsuario() {
        String userName = txtUserName.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        // Actualizamos los datos del objeto Usuario
        usuario.setNombreUsuario(userName);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setCorreo(email);
        usuario.setContraseña(password);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean actualizado = usuarioDAO.actualizarUsuario(usuario);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado con éxito.");
            dispose(); // Cierra la ventana de actualización
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
