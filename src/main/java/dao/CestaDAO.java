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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CerrarConexion(con, stmt, null).cerrar();
        }
    }

    // Método para eliminar un libro de la cesta de un lector
    public void eliminarDeLaCesta(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConexionBD.dameConexion();
            String query = "DELETE FROM Cesta WHERE id_lector = ? AND id_libro = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
            System.out.println("Libro eliminado de la cesta.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CerrarConexion(con, stmt, null).cerrar();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CerrarConexion(con, stmt, rs).cerrar();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new CerrarConexion(con, stmt, rs).cerrar();
        }
        return enCesta;
    }
}

