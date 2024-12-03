package gui;

import javax.swing.*;
import Modelos.Producto;
import Modelos.ProductoDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaActualizarProducto extends JFrame {
    private JTextField txtNombreProducto;
    private JTextField txtMarca;
    private JTextField txtCategoria;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnActualizar;
    private JButton btnCancelar;

    private Producto producto;

    // Constructor que recibe un objeto Producto para llenar los campos de la ventana
    public VentanaActualizarProducto(Producto producto) {
        this.producto = producto; // Guardar el objeto Producto

        // Configuración de la ventana
        setTitle("Actualizar Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Inicializar los componentes y llenarlos con los datos del producto
        JLabel lblNombreProducto = new JLabel("Nombre:");
        txtNombreProducto = new JTextField(producto.getNombreProducto());
        JLabel lblMarca = new JLabel("Marca:");
        txtMarca = new JTextField(producto.getMarcaProducto());
        JLabel lblCategoria = new JLabel("Categoría:");
        txtCategoria = new JTextField(producto.getCategoriaProducto());
        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField(String.valueOf(producto.getPrecioProducto()));
        JLabel lblStock = new JLabel("Stock:");
        txtStock = new JTextField(String.valueOf(producto.getStockProducto()));

        btnActualizar = new JButton("Actualizar");
        btnCancelar = new JButton("Cancelar");

        // Agregar los componentes al JFrame
        add(lblNombreProducto);
        add(txtNombreProducto);
        add(lblMarca);
        add(txtMarca);
        add(lblCategoria);
        add(txtCategoria);
        add(lblPrecio);
        add(txtPrecio);
        add(lblStock);
        add(txtStock);
        add(btnActualizar);
        add(btnCancelar);

        // Acción para actualizar el producto
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        // Acción para cancelar la actualización
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana de actualización
            }
        });
    }

    // Método para actualizar el producto en la base de datos
    private void actualizarProducto() {
        // Obtener los valores de los campos de texto
        String nombreProducto = txtNombreProducto.getText();
        String marca = txtMarca.getText();
        String categoria = txtCategoria.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());

        // Actualizamos el producto con los nuevos datos
        producto.setNombreProducto(nombreProducto);
        producto.setMarcaProducto(marca);
        producto.setCategoriaProducto(categoria);
        producto.setPrecioProducto((int) precio);
        producto.setStockProducto(stock);

        ProductoDAO productoDAO = new ProductoDAO();
        boolean actualizado = productoDAO.actualizarProducto(producto);

        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Producto actualizado con éxito.");
            dispose(); // Cierra la ventana de actualización
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
