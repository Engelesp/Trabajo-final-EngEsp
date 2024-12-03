package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelos.UsuarioDAO;


public class Login extends JFrame {
    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;
    private JButton btnVolver;

    public Login() {
        // Configuración de la ventana
        setTitle("Iniciar Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Inicializar los componentes
        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        txtUsuario = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField();
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnVolver = new JButton("Volver");

        // Agregar los componentes al JFrame
        add(lblUsuario);
        add(txtUsuario);
        add(lblPassword);
        add(txtPassword);
        add(btnIniciarSesion);
        add(btnVolver);

        // Acción al presionar el botón "Iniciar Sesión"
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Acción al presionar el botón "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Inicio().setVisible(true);
                dispose(); // Cierra la ventana de login
            }
        });
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        // Instanciar UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Verificar credenciales en la base de datos
        if (usuarioDAO.verificarCredenciales(usuario, password)) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario);
            new PantallaPrincipal().setVisible(true); // Abre la pantalla principal
            dispose(); // Cierra la ventana de login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login ventana = new Login();
            ventana.setVisible(true);
        });
    }
}
