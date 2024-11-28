package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.ConexionBD;

public class DetallesLibroDAO {

    // Método para agregar un libro a la cesta
    public void agregarACesta(int idLector, int idLibro) {
    	// Variables usadas
        Connection conexion = null;
        PreparedStatement pst = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = ConexionBD.dameConexion();

            // SQL para insertar un libro en la cesta del usuario
            String sql = "INSERT INTO cesta (id_lector, id_libro) VALUES (?, ?)";
            pst = conexion.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            pst.setInt(1, idLector);
            pst.setInt(2, idLibro);

            // Ejecutar la consulta
            pst.executeUpdate();
            
            // Manejo de errores
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro a la cesta: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    // Método para agregar un libro a los favoritos
    public void agregarAFavoritos(int idLector, int idLibro) {
    	// Variables usadas
        Connection conexion = null;
        PreparedStatement pst = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = ConexionBD.dameConexion();

            // SQL para insertar un libro en los favoritos del usuario
            String sql = "INSERT INTO favoritos (id_lector, id_libro) VALUES (?, ?)";
            pst = conexion.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            pst.setInt(1, idLector);
            pst.setInt(2, idLibro);

            // Ejecutar la consulta
            pst.executeUpdate();
            
            // Manejo de errores
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro a favoritos: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}