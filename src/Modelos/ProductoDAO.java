package Modelos;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Método para registrar un producto
    public boolean registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (NombreProducto, MarcaProducto, CategoriaProducto, PrecioProducto, StockProducto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombreProducto());
            stmt.setString(2, producto.getMarcaProducto());
            stmt.setString(3, producto.getCategoriaProducto());
            stmt.setDouble(4, producto.getPrecioProducto());
            stmt.setInt(5, producto.getStockProducto());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
    }

    // Método para listar productos
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Procesar el resultado de la consulta
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("NombreProducto"),
                        rs.getString("MarcaProducto"),
                        rs.getString("CategoriaProducto"),
                        rs.getInt("PrecioProducto"),
                        rs.getInt("StockProducto")
                );
                producto.setIdProducto(rs.getInt("idProducto"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE idProducto = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar un producto
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET NombreProducto = ?, MarcaProducto = ?, CategoriaProducto = ?, PrecioProducto = ?, StockProducto = ? WHERE idProducto = ?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombreProducto());
            stmt.setString(2, producto.getMarcaProducto());
            stmt.setString(3, producto.getCategoriaProducto());
            stmt.setDouble(4, producto.getPrecioProducto());
            stmt.setInt(5, producto.getStockProducto());
            stmt.setInt(6, producto.getIdProducto());  // Asegúrate de que el ID esté presente

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la actualización fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un producto por ID
    public static Producto obtenerProductoPorId(int idProducto) {
        String sql = "SELECT * FROM productos WHERE idProducto = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);  // Establece el idProducto en la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si encontramos el producto, lo devolvemos
                    Producto producto = new Producto(
                        rs.getString("NombreProducto"),
                        rs.getString("MarcaProducto"),
                        rs.getString("CategoriaProducto"),
                        rs.getInt("PrecioProducto"),
                        rs.getInt("StockProducto")
                    );
                    producto.setIdProducto(rs.getInt("idProducto"));
                    return producto;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener producto por ID: " + e.getMessage());
        }
        return null;  // Si no se encuentra el producto, devolvemos null
    }
}
