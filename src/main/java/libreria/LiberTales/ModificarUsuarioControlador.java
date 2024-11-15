package libreria.LiberTales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.ConexionBD;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModificarUsuarioControlador {

	TextField campoNombre;
	TextField campoDireccion;
	TextField campoTelefono;
	private int idLector;

	@FXML
	private void ingresarDatosbbdd() {
		this.idLector = idLector;

		String sql = "SELECT nombre, direccion, telefono FROM Lector WHERE id_lector = ?";

		try (Connection conn = ConexionBD.dameConexion();
			PreparedStatement stmt = conn.prepareStatement(sql)){

			stmt.setInt(1, idLector);
			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				campoNombre.setText(resultado.getString("nombre"));
				campoDireccion.setText(resultado.getString("direccion"));
				campoTelefono.setText(resultado.getString("telefono"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void guardarCambios() {
		String nombre = campoNombre.getText();
		String direccion = campoDireccion.getText();
		String telefono = campoTelefono.getText();

		String sql = "UPDATE Lector SET nombre = ?, direccion = ?, telefono = ? WHERE id_lector = ?";

		try (Connection conn = ConexionBD.dameConexion();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, nombre);
	            stmt.setString(2, direccion);
	            stmt.setString(3, telefono);
	            stmt.setInt(4, idLector);
	            
	            int filasActualizadas = stmt.executeUpdate();
	            
	            if (filasActualizadas > 0) {
	                System.out.println("Datos del lector actualizados correctamente.");
	            } else {
	                System.out.println("No se encontró el lector con el ID especificado.");
	            }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void switchtoModificarUsuario() throws IOException {
		App.setRoot("modificarusuario");
	}
	
	private void switchtoIniciarSesion() throws IOException{
		App.setRoot("iniciarsesion");
	}
}
