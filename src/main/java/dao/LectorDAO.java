package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Lector;
import dto.Usuario;

public class LectorDAO {
	
	public LectorDAO() {
		// TODO Auto-generated constructor stub
	}
	
	 // Método para crear un Lector
    public void crearLector(Lector lector) {
        String sql = "INSERT INTO lector (id_usuario, nombre, direccion, telefono, email, contrasenia) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, lector.getIdusuario());
            pst.setString(2, lector.getNombre());
            pst.setString(3, lector.getDireccion());
            pst.setString(4, lector.getTelefono());
            pst.setString(5, lector.getEmail());
            pst.setString(6, lector.getContrasenia());
            pst.executeUpdate();

            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Método para buscar un Lector por id_usuario
    public int buscarLectorPorId(int idUsuario) {
        String sql = "SELECT id_lector FROM lector WHERE id_usuario = ?";
        int id = 0;

        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idUsuario);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            CerrarConexion.cerrar(conn, pst, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    // Método para modificar un Lector
    public void modificarLector(Lector lector) {
        String sql = "UPDATE lector SET nombre = ?, direccion = ?, telefono = ?, email = ?, contrasenia = ? WHERE id_lector = ?";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, lector.getNombre());
            pst.setString(2, lector.getDireccion());
            pst.setString(3, lector.getTelefono());
            pst.setString(4, lector.getEmail());
            pst.setString(5, lector.getContrasenia());
            pst.setInt(6, lector.getIdLector());
            pst.executeUpdate();

            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un lector
    public void eliminarLector(int id) {
        String sql = "DELETE FROM lector WHERE id_lector = ?";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            pst.executeUpdate();
            
            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un Lector por ID
    public Lector leerLectorPorId(int id) {
        String sql = "SELECT * FROM lector WHERE id_lector = ?";
        Lector lector = null;

        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lector = new Lector(rs.getInt("id_lector"), rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"), rs.getString("contrasenia"));
            }

            CerrarConexion.cerrar(conn, pst, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lector;
    }

    // Método para obtener todos los lectores
    public List<Lector> leerTodosLosLectores() {
        List<Lector> lectores = new ArrayList<>();
        String sql = "SELECT * FROM Lector";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Lector lector = new Lector(rs.getInt("id_lector"), rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"), rs.getString("contrasenia"));
                lectores.add(lector);
            }

            CerrarConexion.cerrar(conn, pst, rs);;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectores;
    }
}
