package Modelos;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // Método para registrar un usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (UserName, Nombre, Apellido, Telefono, Email, Password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getCorreo());
            stmt.setString(6, usuario.getContraseña());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para listar usuarios
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("UserName"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Email"),
                    rs.getString("Password")
                );
                usuario.setId(rs.getInt("idUser"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    // Método para eliminar un usuario
    public static boolean eliminarUsuario(int idUser) {
        String sql = "DELETE FROM usuarios WHERE idUser = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUser);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para verificar las credenciales
    public boolean verificarCredenciales(String usuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE UserName = ? AND Password = ?";
        try (Connection conn = Conexion.getConexion(); // Obtiene la conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);    // Establece el nombre de usuario ingresado
            stmt.setString(2, password);   // Establece la contraseña ingresada

            // Ejecuta la consulta y verifica si se encuentra el usuario
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Si el ResultSet tiene datos, significa que las credenciales son correctas
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET UserName = ?, Nombre = ?, Apellido = ?, Telefono = ?, Email = ?, Password = ? WHERE idUser = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getCorreo());
            stmt.setString(6, usuario.getContraseña());
            stmt.setInt(7, usuario.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la actualización fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE idUser = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);  // Establece el ID
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("UserName"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("Password")
                    );
                    usuario.setId(rs.getInt("idUser")); // Asegúrate de que el ID se asigne aquí
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return null;  // Si no se encuentra el usuario
    }

	}

