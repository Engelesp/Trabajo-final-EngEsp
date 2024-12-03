package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUsuario extends JFrame {
    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmarPassword;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroUsuario() {
        // Configuración de la ventana
        setTitle("Registro de Usuario");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2, 10, 10)); // Usamos un GridLayout para ordenar los elementos

        // Inicializar componentes
        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        txtUsuario = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();

        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        txtCorreo = new JTextField();

        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField();

        JLabel lblConfirmarPassword = new JLabel("Confirmar Contraseña:");
        txtConfirmarPassword = new JPasswordField();

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        // Agregar componentes al JFrame
        add(lblUsuario);
        add(txtUsuario);

        add(lblNombre);
        add(txtNombre);

        add(lblApellido);
        add(txtApellido);

        add(lblTelefono);
        add(txtTelefono);

        add(lblCorreo);
        add(txtCorreo);

        add(lblPassword);
        add(txtPassword);

        add(lblConfirmarPassword);
        add(txtConfirmarPassword);

        add(btnRegistrar);
        add(btnCancelar);

        // Acción al presionar el botón "Registrar"
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inicio().setVisible(true); // Abre la ventana de Inicio
                dispose(); // Cierra la ventana actual
            }
        });
    }

    // Método para registrar el usuario
    private void registrarUsuario() {
        try {
            String usuario = txtUsuario.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String password = new String(txtPassword.getPassword());
            String confirmarPassword = new String(txtConfirmarPassword.getPassword());

            // Validar campos vacíos
            if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar contraseñas
            if (!password.equals(confirmarPassword)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear usuario y enviarlo a la base de datos
            Usuario usuarioObj = new Usuario(usuario, nombre, apellido, telefono, correo, password);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            if (usuarioDAO.registrarUsuario(usuarioObj)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos después de registrar
    private void limpiarCampos() {
        txtUsuario.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        txtConfirmarPassword.setText("");
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroUsuario ventana = new RegistroUsuario();
            ventana.setVisible(true);
        });
    }
}
