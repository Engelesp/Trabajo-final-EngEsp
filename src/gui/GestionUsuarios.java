package gui;

import Modelos.Usuario;
import Modelos.UsuarioDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionUsuarios extends JFrame {
    private JTable tableUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevoUsuario;
    private JButton btnEliminarUsuario;
    private JButton btnActualizarUsuario;
    private JButton btnVolver;

    public GestionUsuarios() {
        // Configuración de la ventana
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla
        modeloTabla = new DefaultTableModel();
        tableUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar columnas a la tabla
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre de Usuario");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Correo");

        // Agregar botones
        JPanel panelBotones = new JPanel();
        btnNuevoUsuario = new JButton("Nuevo Usuario");
        btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnActualizarUsuario = new JButton("Actualizar Usuario");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnNuevoUsuario);
        panelBotones.add(btnEliminarUsuario);
        panelBotones.add(btnActualizarUsuario);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción al presionar "Nuevo Usuario"
        btnNuevoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistroUsuario().setVisible(true); // Abre la ventana de registro de usuario
                dispose(); // Cierra la ventana de gestión de usuarios
            }
        });

        // Acción al presionar "Eliminar Usuario"
        btnEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        // Acción al presionar "Actualizar Usuario"
        btnActualizarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        // Acción al presionar "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaPrincipal().setVisible(true); // Vuelve a la pantalla principal
                dispose(); // Cierra la ventana de gestión de usuarios
            }
        });

        // Cargar los usuarios en la tabla
        cargarUsuarios();
    }

    // Método para cargar los usuarios en la tabla
    private void cargarUsuarios() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        // Limpiar la tabla antes de cargar los datos
        modeloTabla.setRowCount(0);

        // Agregar los usuarios a la tabla
        for (Usuario usuario : usuarios) {
            modeloTabla.addRow(new Object[]{
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getTelefono(),
                usuario.getCorreo()
            });
        }
    }

    // Método para eliminar un usuario seleccionado
    private void eliminarUsuario() {
        int filaSeleccionada = tableUsuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idUser = (int) tableUsuarios.getValueAt(filaSeleccionada, 0);

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este usuario?", "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                if (UsuarioDAO.eliminarUsuario(idUser)) {
                    JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");
                    cargarUsuarios(); // Recarga la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
        }
    }

    // Método para actualizar un usuario seleccionado
    private void actualizarUsuario() {
        int filaSeleccionada = tableUsuarios.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idUsuario = (int) tableUsuarios.getValueAt(filaSeleccionada, 0);
            Usuario usuario = new UsuarioDAO().obtenerUsuarioPorId(idUsuario);

            if (usuario != null) {
                new VentanaActualizarUsuario(usuario).setVisible(true);
                cargarUsuarios(); // Recarga la tabla después de actualizar
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para actualizar.");
        }
    }

        
    

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionUsuarios ventana = new GestionUsuarios();
            ventana.setVisible(true);
        });
    }
}
