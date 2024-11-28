package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Favorito;

public class FavoritoDAO {
	
    // Método para obtener todos los libros favoritos de un lector
    public List<Favorito> obtenerFavoritos(int idLector) {
    	// Variables usadas
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Favorito> favoritos = new ArrayList<>();

        try {
            con = ConexionBD.dameConexion();
            // Sentencia para seleccionar el id de libro de la tabla favoritos cuyo id del lector sea el de la sesion activa
            String query = "SELECT id_libro FROM Favoritos WHERE id_lector = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            rs = stmt.executeQuery();

            while (rs.next()) {
            	// Obetenemos el id de libro 
                int idLibro = rs.getInt("id_libro");
                // Creamos un objeto de tipo favorito y lo agregamos a la lista de favoritos
                favoritos.add(new Favorito(idLector, idLibro));
            }
            
            CerrarConexion.cerrar(con, stmt, null);
            // Manejo de errores
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoritos;
    }
    
    // Método para agregar un libro a la cesta de un lector
    public void agregarALaCesta(int idLector, int idLibro) {
    	//Variables usadas
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
    
    // Método para eliminar un libro de la cesta
    public void eliminarDeFavorito(int idLector, int idLibro) {
    	// Variables usadas
        Connection conexion = null;
        PreparedStatement pst = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = ConexionBD.dameConexion();

            // SQL para eliminar un libro de la cesta del usuario
            String sql = "DELETE FROM favoritos WHERE id_lector = ? AND id_libro = ?";
            pst = conexion.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            pst.setInt(1, idLector);
            pst.setInt(2, idLibro);

            // Ejecutar la consulta
            pst.executeUpdate();
            
            //Manejo de errores
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro de favorito: " + e.getMessage());
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
