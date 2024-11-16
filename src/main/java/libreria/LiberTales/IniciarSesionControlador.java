package libreria.LiberTales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alertas.Alerta;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.SesionUsuario;
import dto.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IniciarSesionControlador{

    @FXML
    private ImageView logoImageView;
    @FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField searchField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button addButton;
    @FXML
    private Hyperlink forgotPasswordLink;
    @FXML
    private Label footerLabel;
	
    @FXML
    public void comprobarUsuario(ActionEvent event) {
    	 String email = emailField.getText();
         String password = passwordField.getText();
         
         UsuarioDAO us = new UsuarioDAO();
         LectorDAO ldao = new LectorDAO();
         
         if (email.isEmpty() || password.isEmpty()) {
             Alerta.mostrarError("Error al iniciar sesión", "Por favor, rellena los campos vacios");
             return;
         } 
         
         Usuario usuario = us.autenticarUsuario(email, password);
         
         if(usuario==null) {
        	 Alerta.mostrarError("Error al iniciar sesión", "Email o contraseña incorrectos.");
        	 
         } else if(usuario.getTipo().equals("administrador")){
        	 try {
        		 App.setRoot("administracion");
			} catch (IOException e) {
				e.printStackTrace();
			}
         } else if(usuario.getTipo().equals("lector")){
        	 try {
        	    // Guarda el idLector en la sesión
        	    SesionUsuario.getInstancia().setIdLector(ldao.buscarLectorPorId(usuario.getIdUsuario()));
        	    System.out.println(SesionUsuario.getInstancia().getIdLector());
        	    // Cambia a la página principal
        	    App.setRoot("paginaprincipal"); 
        	 }catch (IOException e) {
				e.printStackTrace();
			}
         }

	}
    
    @FXML
    private void switchToPagina() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    @FXML
    private void switchToRegistarse() throws IOException {
    	try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrarse.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    @FXML
    private void switchToOlvidarse() throws IOException {
    	try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("olvidar.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    @FXML
	private void switchtoLogin() throws IOException {
    	if(SesionUsuario.getInstancia().getIdLector()==null) {
    		try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
		        Parent root = loader.load();
		        Stage stage = (Stage) userButton.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} else {
			try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("modificarusuario.fxml"));
		        Parent root = loader.load();
		        Stage stage = (Stage) userButton.getScene().getWindow();
		        stage.setScene(new Scene(root));
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
		        Stage stage = (Stage) cartButton.getScene().getWindow();
		        stage.setScene(new Scene(root));
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
		        Stage stage = (Stage) messageButton.getScene().getWindow();
		        stage.setScene(new Scene(root));
		        stage.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		} else {
			Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
		}
		
	}
	
	
   	
}
