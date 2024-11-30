package libreria.LiberTales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CambiarContrasenaController {
	
	@FXML 
	private TextField campoContrasenaActual;
	@FXML 
	private TextField campoNuevaContrasena;
	@FXML 
	private TextField campoVerificarContrasena;
	@FXML
	private Button botonGuardarContrasena;
	private int idLector = SesionUsuario.getInstancia().getIdLector();
	static CambiarContrasenaController controlador;
	@FXML
	private Label errorContrasenaActual;
	@FXML
	private Label errorNuevaContrasena;
	@FXML
	private Label errorVerificarContrasena;
	
	public void initialize() {
		String contrasenaActual = campoContrasenaActual.getText();
		String contrasenaNueva = campoNuevaContrasena.getText();
		String verificarContrasena = campoVerificarContrasena.getText();
		
		// Comprobar que todos los campos están rellenos
				if (contrasenaActual.isEmpty() || contrasenaNueva.isEmpty() || verificarContrasena.isEmpty()) {
					campoContrasenaActual.setStyle("-fx-border-color: red;");
		            errorContrasenaActual.setText("Por favor, completa todos los campos");
				}else {
					campoContrasenaActual.setStyle(null); // Limpia estilos de error
		            errorContrasenaActual.setText(""); // Limpia el mensaje
				}
		        
				// Comprobar que la contraseña actual (antes de cambiarla) es correcta
				if (!verificarContrasenaActual(contrasenaActual)) {
			        campoContrasenaActual.setStyle("-fx-border-color: red;");
			        errorContrasenaActual.setText("La contraseña no es correcta");
			    }else if(verificarContrasenaActual(contrasenaActual)) {
			    	campoContrasenaActual.setStyle(null); // Limpia estilos de error
		            errorContrasenaActual.setText(""); // Limpia el mensaje
			    }
				
				// Listener para validar el campo de contraseña en tiempo real
		        campoNuevaContrasena.textProperty().addListener((observable, oldValue, newValue) -> {
		            if (newValue.trim().isEmpty()) {
		                campoNuevaContrasena.setStyle("-fx-border-color: red;");
		                errorNuevaContrasena.setText("La contraseña no puede estar vacía");
		            } else {
		                campoNuevaContrasena.setStyle(null); // Limpia estilos de error
		                errorNuevaContrasena.setText(""); // Limpia el mensaje
		            }
		        });
				
			    }
	
	@FXML
	private void guardarContrasena() {
		String contrasenaActual = campoContrasenaActual.getText();
		String contrasenaNueva = campoNuevaContrasena.getText();
		String verificarContrasena = campoVerificarContrasena.getText();
		
		// Comprobar que la contraseña nueva y su verificación son iguales
    	if (!contrasenaNueva.equals(verificarContrasena)) {
    	campoVerificarContrasena.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(campoNuevaContrasena.getText())) {
                campoVerificarContrasena.setStyle("-fx-border-color: red;");
                errorVerificarContrasena.setText("Las contraseñas no coinciden");
            } else {
                campoVerificarContrasena.setStyle(null); // Limpia estilos de error
                errorVerificarContrasena.setText(""); // Limpia el mensaje
            }
        });
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
		
		Stage stage = (Stage) botonGuardarContrasena.getScene().getWindow();
		stage.close();
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
