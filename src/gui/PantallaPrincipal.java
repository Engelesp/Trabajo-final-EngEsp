package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {
    // Componentes de la interfaz
    private JButton btnGestionUsuarios;
    private JButton btnGestionProductos;
    private JButton btnCerrarSesion;

    public PantallaPrincipal() {
        // Configuración de la ventana
        setTitle("Pantalla Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10)); // Usamos un GridLayout para organizar los botones

        // Inicializar componentes
        btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionProductos = new JButton("Gestión de Productos");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Agregar botones al JFrame
        add(btnGestionUsuarios);
        add(btnGestionProductos);
        add(btnCerrarSesion);

        // Acción al presionar el botón "Gestión de Usuarios"
        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos a la ventana de Gestión de Usuarios
                new GestionUsuarios().setVisible(true);
                dispose(); // Cierra la pantalla principal
            }
        });

        // Acción al presionar el botón "Gestión de Productos"
        btnGestionProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos a la ventana de Gestión de Productos
                new GestionProductos().setVisible(true);
                dispose(); // Cierra la pantalla principal
            }
        });

        // Acción al presionar el botón "Cerrar Sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la pantalla principal y retorna al login
                JOptionPane.showMessageDialog(null, "Sesión cerrada exitosamente.");
                dispose(); // Cierra la ventana
                new Login().setVisible(true); // Regresa al login
            }
        });
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaPrincipal ventana = new PantallaPrincipal();
            ventana.setVisible(true);
        });
    }
}
