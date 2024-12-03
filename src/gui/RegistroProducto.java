package gui;

import Modelos.Producto;
import Modelos.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroProducto extends JFrame {
    // Componentes de la interfaz
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtCategoria;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroProducto() {
        // Configuración de la ventana
        setTitle("Registro de Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10)); // Usamos un GridLayout para ordenar los elementos

        // Inicializar componentes
        JLabel lblNombre = new JLabel("Nombre del Producto:");
        txtNombre = new JTextField();

        JLabel lblMarca = new JLabel("Marca:");
        txtMarca = new JTextField();

        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField();

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();

        JLabel lblStock = new JLabel("Cantidad Disponible:");
        txtStock = new JTextField();

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        // Agregar componentes al JFrame
        add(lblNombre);
        add(txtNombre);

        add(lblMarca);
        add(txtMarca);

        add(lblCategoria);
        add(txtCategoria);

        add(lblPrecio);
        add(txtPrecio);

        add(lblStock);
        add(txtStock);

        add(btnRegistrar);
        add(btnCancelar);

        // Acción al presionar el botón "Registrar"
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });

        // Acción al presionar el botón "Cancelar"
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
    }

    // Método para registrar el producto
    private void registrarProducto() {
        try {
            String nombre = txtNombre.getText();
            String marca = txtMarca.getText();
            String categoria = txtCategoria.getText();
            int precio = Integer.parseInt(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            // Validar campos vacíos
            if (nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear producto y enviarlo a la base de datos
            Producto producto = new Producto(nombre, marca, categoria, precio, stock);
            ProductoDAO productoDAO = new ProductoDAO();
            if (productoDAO.registrarProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos en los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos después de registrar
    private void limpiarCampos() {
        txtNombre.setText("");
        txtMarca.setText("");
        txtCategoria.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroProducto ventana = new RegistroProducto();
            ventana.setVisible(true);
        });
    }
}
