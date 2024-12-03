package gui;

import Modelos.Producto;
import Modelos.ProductoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionProductos extends JFrame {
    private JTable tableProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevoProducto;
    private JButton btnEliminarProducto;
    private JButton btnActualizarProducto;
    private JButton btnVolver;

    public GestionProductos() {
        // Configuración de la ventana
        setTitle("Gestión de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla
        modeloTabla = new DefaultTableModel();
        tableProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar columnas a la tabla
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Categoría");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Stock");

        // Agregar botones
        JPanel panelBotones = new JPanel();
        btnNuevoProducto = new JButton("Nuevo Producto");
        btnEliminarProducto = new JButton("Eliminar Producto");
        btnActualizarProducto = new JButton("Actualizar Producto");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnNuevoProducto);
        panelBotones.add(btnEliminarProducto);
        panelBotones.add(btnActualizarProducto);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción al presionar "Nuevo Producto"
        btnNuevoProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistroProducto().setVisible(true); 
                dispose();
            }
        });

        // Acción al presionar "Eliminar Producto"
        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        // Acción al presionar "Actualizar Producto"
        btnActualizarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        // Acción al presionar "Volver"
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaPrincipal().setVisible(true); 
                dispose(); 
            }
        });

        
        cargarProductos();
    }

    // Método para cargar los productos en la tabla
    private void cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductos();

        
        modeloTabla.setRowCount(0);

        // Agregar los productos a la tabla
        for (Producto producto : productos) {
            modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getMarcaProducto(),
                    producto.getCategoriaProducto(),
                    producto.getPrecioProducto(),
                    producto.getStockProducto()
            });
        }
    }

    // Método para eliminar un producto seleccionado
    private void eliminarProducto() {
        int filaSeleccionada = tableProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idProducto = (int) tableProductos.getValueAt(filaSeleccionada, 0);

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar este producto?", "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                ProductoDAO productoDAO = new ProductoDAO();
                if (productoDAO.eliminarProducto(idProducto)) {
                    JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
                    cargarProductos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
        }
    }

    // Método para actualizar un producto seleccionado
    private void actualizarProducto() {
        int filaSeleccionada = tableProductos.getSelectedRow();
        if (filaSeleccionada != -1) {       
            int idProducto = (int) tableProductos.getValueAt(filaSeleccionada, 0);

            Producto producto = ProductoDAO.obtenerProductoPorId(idProducto);

            if (producto != null) {       
                new VentanaActualizarProducto(producto).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo encontrar el producto.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para actualizar.");
        }
    }


    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionProductos ventana = new GestionProductos();
            ventana.setVisible(true);
        });
    }

	public static int getSelectedRow() {
		// TODO Auto-generated method stub
		return 0;
	}
}
