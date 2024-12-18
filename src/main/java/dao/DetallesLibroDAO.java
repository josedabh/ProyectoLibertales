package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
    
    // Metodo de alquiler
    public void agregarAlquiler(int idLector, int idLibro) {
        Connection conexion = null;
        PreparedStatement pst = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = ConexionBD.dameConexion();

            // SQL corregido: Sin la coma extra
            String sql = "INSERT INTO alquiler (id_lector, id_libro, fecha_inicio, fecha_limite) VALUES (?, ?, ?, ?)";

            pst = conexion.prepareStatement(sql);

            // Obtener fechas: inicio (hoy) y límite (15 días después)
            LocalDate fechaInicio = LocalDate.now();
            LocalDate fechaLimite = fechaInicio.plusDays(15);

            // Establecer los parámetros de la consulta
            pst.setInt(1, idLector);
            pst.setInt(2, idLibro);
            pst.setDate(3, java.sql.Date.valueOf(fechaInicio));
            pst.setDate(4, java.sql.Date.valueOf(fechaLimite));

            // Ejecutar la consulta
            pst.executeUpdate();

            System.out.println("El libro se agregó a alquiler correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el libro a alquiler: " + e.getMessage());
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