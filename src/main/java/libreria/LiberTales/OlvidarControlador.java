package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.OlvidarDAO;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OlvidarControlador {
	
	@FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavorito;
    @FXML
    private Button botonAtras;
	
    @FXML
    private TextField correo;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private Button botonEnviar;
    
    @FXML
    private Label errorCorreo;
    
    @FXML
    private Label errorNombre;
    
    @FXML
    public void initialize() {
        // Listener para validar el campo de correo en tiempo real
        correo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!esCorreoValido(newValue)) {
                correo.setStyle("-fx-border-color: red;");
                errorCorreo.setText("Por favor, introduce un correo válido");
            } else {
                correo.setStyle(null); // Limpia estilos de error
                errorCorreo.setText(""); // Limpia el mensaje
            }
        });

        // Listener para validar el campo de nombre en tiempo real
        nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                nombre.setStyle("-fx-border-color: red;");
                errorNombre.setText("El nombre no puede estar vacío");
            } else {
                nombre.setStyle(null); // Limpia estilos de error
                errorNombre.setText(""); // Limpia el mensaje
            }
        });
    }

    @FXML
   	private void switchtoLogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Iniciar sesión");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
       	} else if(SesionAdmin.getInstancia().getIdAdmin()!=null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Administración");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("modificarusuario.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Modificar usuario");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		}
   	}
	
    @FXML
   	private void switchToCesta() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonCesta.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Cesta");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir a la cesta", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
	
    @FXML
   	private void switchToFavorito() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonFavorito.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Favoritos");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
	
	@FXML
    private void switchToPagina() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) botonAtras.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Página principal");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
	@FXML
	public void restablecerContrasenia() {
		String email = correo.getText().trim();
		String usuario = nombre.getText().trim();
		
		// Validaciones finales antes de enviar
		if (email.isEmpty() || usuario.isEmpty()) {
			errorCorreo.setText("Por favor, rellene todos los campos");
			return;
		}

		if (!esCorreoValido(email)) {
			errorCorreo.setText("Por favor, introduce un correo válido");
			return;
		}
		
		try {
			OlvidarDAO dao = new OlvidarDAO();
			dao.recuperarContrasenia(email, usuario);
			Alerta.mostrarInformacion("Tu nueva contraseña es: ", "123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean esCorreoValido(String email) {
		// Expresión regular básica para validar correos electrónicos
		return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
	}
}

