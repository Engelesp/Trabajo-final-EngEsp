package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://almacenitla-db.ctam6uiuy8ez.us-east-1.rds.amazonaws.com:3306/almacenitlafinal";
    private static final String USUARIO = "estuditlafinal";
    private static final String CLAVE = "itla123.";

    static {
        try {
            Class.forName(CONTROLADOR);
        } catch (Exception e) {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
    }

    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a la base de datos.");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }

    public static Connection getConexion() {
        Conexion conexionDB = new Conexion(); 
        return conexionDB.conectar();
    }
}