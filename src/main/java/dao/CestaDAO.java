package dao;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Cesta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CestaDAO {

    // Método para agregar un libro a la cesta de un lector
    public void agregarALaCesta(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ConexionBD.dameConexion();
            String query = "INSERT INTO Cesta (id_lector, id_libro) VALUES (?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
            System.out.println("Libro agregado a la cesta.");
            
            CerrarConexion.cerrar(con, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro de la cesta de un lector
    public void eliminarDeLaCesta(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConexionBD.dameConexion();
            String query = "DELETE FROM cesta WHERE id_lector = ? AND id_libro = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
            
            CerrarConexion.cerrar(con, stmt, null);
            System.out.println("Libro eliminado de la cesta.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Método para alquilar un libro en la cesta de un lector
    public void alquilarLibro(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConexionBD.dameConexion();
            String query = "UPDATE Cesta SET estado = 'alquilado' WHERE id_lector = ? AND id_libro = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Libro alquilado correctamente.");
            } else {
                System.out.println("El libro no estaba en la cesta del lector.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CerrarConexion.cerrar(con, stmt, null);
        }
    }

    // Método para obtener todos los libros en la cesta de un lector
    public List<Cesta> obtenerCesta(int idLector) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cesta> cesta = new ArrayList<>();

        try {
            con = ConexionBD.dameConexion();
            String query = "SELECT id_libro FROM Cesta WHERE id_lector = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("id_libro");
                cesta.add(new Cesta(idLector, idLibro));
            }
            
            CerrarConexion.cerrar(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cesta;
    }

    // Método para verificar si un libro está en la cesta de un lector
    public boolean estaEnCesta(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean enCesta = false;

        try {
            con = ConexionBD.dameConexion();
            String query = "SELECT COUNT(*) FROM Cesta WHERE id_lector = ? AND id_libro = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            rs = stmt.executeQuery();

            if (rs.next()) {
                enCesta = rs.getInt(1) > 0;
            }
            
            CerrarConexion.cerrar(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enCesta;
    }
    
 // Método para agregar un libro a la cesta
    public void agregarACesta(int idLector, int idLibro) {
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
}

