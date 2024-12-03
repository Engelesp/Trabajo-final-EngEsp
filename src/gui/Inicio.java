package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {
    // Componentes de la interfaz
    private JButton btnLogin;
    private JButton btnRegistrarse;

    public Inicio() {
        // Configuración de la ventana
        setTitle("Inicio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Inicializar los botones
        btnLogin = new JButton("Iniciar Sesión");
        btnRegistrarse = new JButton("Registrarse");

        // Agregar los botones a la ventana
        add(btnLogin);
        add(btnRegistrarse);

        // Acción al presionar el botón "Iniciar Sesión"
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de Login
                new Login().setVisible(true);
                dispose(); // Cierra la pantalla de inicio
            }
        });

        // Acción al presionar el botón "Registrarse"
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de Registro de Usuario
                new RegistroUsuario().setVisible(true);
                dispose(); // Cierra la pantalla de inicio
            }
        });
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio ventana = new Inicio();
            ventana.setVisible(true);
        });
    }
}
