package libreria.LiberTales;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Alertas.Alerta;
import dao.UsuarioDAO;
import dto.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
         
         if (email.isEmpty() || password.isEmpty()) {
             Alerta.mostrarError("Error al iniciar sesión", "Por favor, rellena los campos vacios");
             return;
         } 
         
         Usuario usuario = us.autenticarUsuario(email, password);
         
         if(usuario==null) {
        	 Alerta.mostrarError("Error al iniciar sesión", "Email o contraseña incorrectos.");
         } else if(usuario.getTipo().equals("administrador")){
        	 System.out.println("Funciona admin");
        	 try {
        		 App.setRoot("administracion");
			} catch (IOException e) {
				e.printStackTrace();
			}
         } else if(usuario.getTipo().equals("lector")){
        	 System.out.println("Funciona Lec");
        	 try {
        		 App.setRoot("paginaprincipal");
			} catch (IOException e) {
				e.printStackTrace();
			}
         }

	}
    
    @FXML
    private void switchToPagina() throws IOException {
        App.setRoot("paginaPrincipal");
    }
    
    @FXML
    private void switchToRegistarse() throws IOException {
		App.setRoot("registrarse");
	}
}
