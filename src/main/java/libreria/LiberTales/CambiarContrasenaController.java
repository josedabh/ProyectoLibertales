package libreria.LiberTales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CambiarContrasenaController {
	
	@FXML private TextField campoContrasenaActual;
	@FXML private TextField campoNuevaContrasena;
	@FXML private TextField campoVerificarContrasena;
	private int idLector = SesionUsuario.getInstancia().getIdLector();
	static CambiarContrasenaController controlador;
	
	@FXML
	private void guardarContrasena() {
		String contrasenaActual = campoContrasenaActual.getText();
		String contrasenaNueva = campoNuevaContrasena.getText();
		String verificarContrasena = campoVerificarContrasena.getText();
		
		// Comprobar que todos los campos están rellenos
		if (contrasenaActual.isEmpty() || contrasenaNueva.isEmpty() || verificarContrasena.isEmpty()) {
		    Alerta.mostrarError("Casillas Vacías", "Todos los campos deben estar llenos");
		    return;
		// Comprobar que la contraseña actual (antes de cambiarla) es correcta
		}else if (!verificarContrasenaActual(contrasenaActual)) {
	        Alerta.mostrarError("Contraseña Actual Erronea", "La contraseña actual no es correcta");
	        return;
	    // Comprobar que la contraseña nueva y su verificación son iguales
	    }else if (!contrasenaNueva.equals(verificarContrasena)) {
	    	Alerta.mostrarError("Contraseña Nueva No Coincide", "La contraseña nueva y su verificación no coinciden");
	        return;
	    }
		
		String sql = "UPDATE Lector SET contrasenia=? WHERE id_lector = ?";

		try (Connection conn = ConexionBD.dameConexion();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, contrasenaNueva);
	            stmt.setInt(2, idLector);
	            
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
	
	protected boolean verificarContrasenaActual(String contrasenaActual) {
	    String sql = "SELECT contrasenia FROM Lector WHERE id_lector = ?";
	    try (Connection conn = ConexionBD.dameConexion();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setInt(1, idLector);
	        ResultSet resultado = stmt.executeQuery();

	        if (resultado.next()) {
	            String contrasenaGuardada = resultado.getString("contrasenia");
	            // Compara la contraseña ingresada con la almacenada
	            return contrasenaGuardada.equals(contrasenaActual);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public void setCambiarContrasena(CambiarContrasenaController controlador) {
		this.controlador = controlador;
	}

}
