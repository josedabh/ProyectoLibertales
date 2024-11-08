package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Administrador;
import dto.Usuario;

public class AdministradorDAO {
	
	public AdministradorDAO() {
		// TODO Auto-generated constructor stub
	}

	// Método para crear un administrador
    public void crearAdministrador(Administrador administrador) {
        String sql = "INSERT INTO administrador (id_usuario, nombre, email, contrasenia) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, administrador.getIdUsuario());
            pst.setString(2, administrador.getNombre());
            pst.setString(3, administrador.getEmail());
            pst.setString(4, administrador.getContrasenia());
            pst.executeUpdate();
            
            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    // Método para modificar un administrador
    public void modificarAdministrador(Administrador administrador) {
        String sql = "UPDATE Administrador SET nombre = ?, email = ?, contrasenia = ? WHERE id_admin = ?";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, administrador.getNombre());
            pst.setString(2, administrador.getEmail());
            pst.setString(3, administrador.getContrasenia());
            pst.setInt(4, administrador.getIdAdmin());
            pst.executeUpdate();

            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un administrador
    public void eliminarAdministrador(int id) {
        String sql = "DELETE FROM administrador WHERE id_admin = ?";
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

    // Método para leer un administrador por ID
    public Administrador leerAdministradorPorId(int id) {
        String sql = "SELECT * FROM administrador WHERE id_admin = ?";
        Administrador administrador = null;

        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                administrador = new Administrador(
                    rs.getInt("id_admin"),
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contrasenia")
                );
            }

            CerrarConexion.cerrar(conn, pst, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    // Método para obtener todos los administradores
    public List<Administrador> leerTodosLosAdministradores() {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM administrador";
        try {
            Connection conn = ConexionBD.dameConexion();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                administradores.add(new Administrador(
                        rs.getInt("id_admin"),
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("contrasenia")));
            }

            CerrarConexion.cerrar(conn, pst, rs);;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administradores;
    }
    
}
