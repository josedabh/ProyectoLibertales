package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Usuario;

public class UsuarioDAO {

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}
	
	//Metodo crear usuario
	public void crearUsuario(Usuario usuario){
        String sql = "INSERT INTO Usuario (email, contrasenia, tipo) VALUES (?, ?, ?)";
        try {
        	Connection conn = ConexionBD.dameConexion();
			PreparedStatement pst = conn.prepareStatement(sql);
        	
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, usuario.getTipo());
            pst.executeUpdate();
            
            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }

    // Metodo para modificar un usuario
    public void modificarUsuario(Usuario usuario){
        String sql = "UPDATE usuario SET email = ?, contrasenia = ?, tipo = ? WHERE id_usuario = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
			PreparedStatement pst = conn.prepareStatement(sql);
        	
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, usuario.getTipo());
            pst.setInt(4, usuario.getIdUsuario());
            pst.executeUpdate();
            
            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }

    // Metodo para eliminar un usuario
    public void eliminarUsuario(int id){
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        try{
        	Connection conn = ConexionBD.dameConexion();
			PreparedStatement pst = conn.prepareStatement(sql);
			
            pst.setInt(1, id);
            pst.executeUpdate();
            
            CerrarConexion.cerrar(conn, pst, null);
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

    //Metodo para leer un usuario por id
    public Usuario leerUsuarioPorId(int id){
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";
        Usuario usuario = null;
        
        try {
        	Connection conn = ConexionBD.dameConexion();
			PreparedStatement pst = conn.prepareStatement(sql);
			
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("email"), rs.getString("contrasenia"), rs.getString("tipo"));
            }
            
            CerrarConexion.cerrar(conn, pst, rs);
        } catch (SQLException e) {
			e.printStackTrace();
		}
        
        return usuario;
    }

    // Metodo para obtener todos los usuarios
    public List<Usuario> leerTodosLosUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try {
        	Connection conn = ConexionBD.dameConexion();
			PreparedStatement pst = conn.prepareStatement(sql);
			
        	ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id_usuario"), rs.getString("email"), rs.getString("contrasenia"), rs.getString("tipo")));
            }
            
            CerrarConexion.cerrar(conn, pst, rs);
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return usuarios;
    }
    
    public Usuario autenticarUsuario(String email,String contrasenia) {
    	 String sql = "SELECT * FROM usuario WHERE email = ? AND contrasenia = ?";
         Usuario usuario = null;
         
         try {
        	Connection conn = ConexionBD.dameConexion();
 			PreparedStatement pst = conn.prepareStatement(sql);
 			
 			pst.setString(1, email);
		    pst.setString(2, contrasenia);
		    ResultSet rs = pst.executeQuery();
		    if (rs.next()) {
		        usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("email"), rs.getString("contrasenia"), rs.getString("tipo"));
		    }
		     
		     CerrarConexion.cerrar(conn, pst, rs);
         } catch (SQLException e) {
 			e.printStackTrace();
 		}
         
        return usuario;
    }
}

