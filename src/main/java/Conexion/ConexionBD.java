package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static Connection dameConexion() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/libertales";
        String usuario = "root"; 
        String contraseña = "123456"; 

        try {
            con = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión establecida con éxito!");


        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        return con;
    }
}

