package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Alquiler;
import dto.HistorialAlquiler;

public class AlquilerDAO {

	// Metodo para obtener los libros alquilados de los lectores
	public List<Alquiler> obtenerAlquileres(int idLector) {
	    // Variables usadas
	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    List<Alquiler> alquileres = new ArrayList<>();

	    try {
	        // Establecer la conexión con la base de datos
	        con = ConexionBD.dameConexion();
	        
	        // Consulta SQL para obtener los alquileres del lector
	        String query = "SELECT id_libro, fecha_inicio, fecha_limite FROM alquiler WHERE id_lector = ?";
	        stmt = con.prepareStatement(query);
	        
	        // Establecer el parámetro de la consulta (idLector)
	        stmt.setInt(1, idLector);
	        
	        // Ejecutar la consulta
	        rs = stmt.executeQuery();
	        
	        // Recorrer el resultado de la consulta
	        while (rs.next()) {
	            int idLibro = rs.getInt("id_libro");
	            java.sql.Date fechaInicio = rs.getDate("fecha_inicio");
	            java.sql.Date fechaLimite = rs.getDate("fecha_limite");
	            
	            // Crear un objeto Alquiler y agregarlo a la lista
	            Alquiler alquiler = new Alquiler(idLector, idLibro, idLibro, fechaInicio.toLocalDate(), fechaLimite.toLocalDate());
	            alquileres.add(alquiler);
	        }
	        
	        // Cerrar los recursos
	        CerrarConexion.cerrar(con, stmt, rs);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();  // Capturar cualquier error de SQL
	    }
	    
	    // Retornar la lista de alquileres
	    return alquileres;
	}

		// Metodo para obtener los libros alquilados de los lectores de forma que la fecha reciente este arriba
		public List<HistorialAlquiler> obtenerListaDescendente() {
		    // Variables usadas
		    Connection con = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    List<HistorialAlquiler> alquileres = new ArrayList<>();

		    try {
		        // Establecer la conexión con la base de datos
		        con = ConexionBD.dameConexion();
		        
		        // Consulta SQL para obtener los alquileres del lector
		        String query = "SELECT lector.nombre, libro.titulo, alquiler.fecha_inicio,alquiler.fecha_limite FROM alquiler "
		        		+ "JOIN lector ON alquiler.id_lector = lector.id_lector "
		        		+ "JOIN libro ON alquiler.id_libro = libro.id_libro "
		        		+ "ORDER BY alquiler.fecha_inicio DESC;";
		        stmt = con.prepareStatement(query);
		        
		        // Ejecutar la consulta
		        rs = stmt.executeQuery();
		        
		        // Recorrer el resultado de la consulta
		        while (rs.next()) {
		            String nomLector = rs.getString("lector.nombre");
		            String tituloLibro = rs.getString("libro.titulo");
		            String fechaInicio = String.valueOf(rs.getDate("fecha_inicio"));
		            String fechaLimite = String.valueOf(rs.getDate("fecha_limite"));
		            
		            HistorialAlquiler histAlquiler = new HistorialAlquiler(nomLector, tituloLibro, fechaInicio, fechaLimite);
		            alquileres.add(histAlquiler);
		        }
		        
		        // Cerrar los recursos
		        CerrarConexion.cerrar(con, stmt, rs);
		        
		    } catch (SQLException e) {
		        e.printStackTrace();  // Capturar cualquier error de SQL
		    }
		    
		    // Retornar la lista de alquileres
		    return alquileres;
		}
}
