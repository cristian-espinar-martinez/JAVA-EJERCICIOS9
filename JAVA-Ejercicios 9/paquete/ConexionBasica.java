package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBasica {
    private static final String URL = "jdbc:mysql://localhost:3306/JAVAPOO"; // URL de la base de datos
    private static final String USUARIO = "root"; // Usuario de la base de datos
    private static final String CONTRASEÑA = "1234"; // Contraseña de la base de datos

    public static Connection obtenerConexion() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            throw e; 
        }
    }
}