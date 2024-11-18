package libreria.LiberTales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModificarUsuarioControlador {

	@FXML private TextField campoNombre;
	@FXML private TextField campoDireccion;
	@FXML private TextField campoTelefono;
	@FXML private Button cartButton;
	@FXML private Button botonGuardarCambios;
	@FXML private Button botonCerrarSesion;
	private int idLector = SesionUsuario.getInstancia().getIdLector();
	boolean anadido = false;

	public void initialize() {
		if (idLector == 0) {
            System.out.println("El ID del lector no ha sido inicializado.");
            return;
        }

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
		
		if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
		    System.out.println("Todos los campos deben estar llenos");
		    return;
		}

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
	                anadido = true;
	            } else {
	                System.out.println("No se encontró el lector con el ID especificado.");
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if(anadido) {
        	try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
    	        Parent root = loader.load();
    	        Stage stage = (Stage) botonGuardarCambios.getScene().getWindow();
    	        stage.setScene(new Scene(root));
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
        } else {
        	Alerta.mostrarError("Error", "Introduce los datos primero");
        }
		
		
	
	}

	@FXML
	private void switchtoModificarUsuario() throws IOException {
		App.setRoot("modificarusuario");
	}
	
	@FXML
	private void switchtoIniciarSesion() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	//copy
	@FXML
	private void switchtoLogin() throws IOException {
		if(SesionUsuario.getInstancia().getIdLector()==null) {
			App.setRoot("iniciarsesion");
		} else {
			App.setRoot("modificarusuario");
		}
	}
	
	@FXML
	private void switchToCesta() throws IOException {
	    // Crear una instancia de FXMLLoader y cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
	    Parent root = loader.load();
	    
	    // Obtener el controlador de la vista cargada
	    CestaControlador controller = loader.getController();
	    
	    // Mostrar la nueva escena
	    Stage stage = (Stage) cartButton.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito");
	}
	
	@FXML
	private void switchToCambiarContrasena() throws IOException {
	    // Cargo la vista
		FXMLLoader loader = new FXMLLoader(getClass().getResource("cambiarcontrasena.fxml"));
	
		 // Cargo la ventana
		Parent root = loader.load();
	
		CambiarContrasenaController controlador = loader.getController();
	
		controlador.setCambiarContrasena(controlador);
	
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
    private void cerrarSesion() throws IOException {
        // Limpiar el idLector de la sesión
        SesionUsuario.getInstancia().cerrarSesion();

        // Redirigir al usuario a la pantalla de inicio de sesión
        App.setRoot("paginaprincipal");
    }
}
