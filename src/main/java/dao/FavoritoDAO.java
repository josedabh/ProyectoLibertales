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
	
	public FavoritoDAO() {
		// TODO Auto-generated constructor stub
	}
	
    // Método para agregar un libro a los favoritos de un lector
    public void agregarAFavoritos(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = ConexionBD.dameConexion();
            String query = "INSERT INTO Favoritos (id_lector, id_libro) VALUES (?, ?)";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
            System.out.println("Libro agregado a favoritos.");
            
            CerrarConexion.cerrar(con, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los libros favoritos de un lector
    public List<Favorito> obtenerFavoritos(int idLector) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Favorito> favoritos = new ArrayList<>();

        try {
            con = ConexionBD.dameConexion();
            String query = "SELECT id_libro FROM Favoritos WHERE id_lector = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("id_libro");
                favoritos.add(new Favorito(idLector, idLibro));
            }
            
            CerrarConexion.cerrar(con, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoritos;
    }

    // Método para verificar si un libro está en los favoritos de un lector
    public boolean estaEnFavoritos(int idLector, int idLibro) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean enFavoritos = false;

        try {
            con = ConexionBD.dameConexion();
            String query = "SELECT COUNT(*) FROM Favoritos WHERE id_lector = ? AND id_libro = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            stmt.setInt(2, idLibro);
            rs = stmt.executeQuery();

            if (rs.next()) {
                enFavoritos = rs.getInt(1) > 0;
            }
            
            CerrarConexion.cerrar(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enFavoritos;
    }
 // Método para eliminar un libro de la cesta
    public void eliminarDeFavorito(int idLector, int idLibro) {
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
