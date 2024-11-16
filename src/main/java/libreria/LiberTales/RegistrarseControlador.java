package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.Lector;
import dto.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrarseControlador {

	@FXML
	private TextField usernameCompletField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField verifyPasswordField;
    @FXML
    private TextArea direccionField;
    @FXML
    private Button registerButton;
    @FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button backButton;

    // Método que se ejecuta cuando el usuario hace clic en "Registrar"
    @FXML
    private void registrarUsuario(ActionEvent event) {
    	String usernameComplet = usernameCompletField.getText();
    	String direccion = direccionField.getText();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = verifyPasswordField.getText();
        String telefono = telefonoField.getText();
        
        // Validar que los campos no estén vacíos
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || 
        		direccion.isEmpty() || usernameComplet.isEmpty() || telefono.isEmpty()) {
            Alerta.mostrarError("Error de Registro", "Por favor, completa todos los campos.");
            return;
        }

        // Verificar que las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
        	 Alerta.mostrarError("Error de Registro", "Las contraseñas no coinciden.");
            return;
        }

        // Validar que el correo no esté registrado ya en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        if (usuarioDAO.obtenerUsuarioPorEmail(email) != null) {
//        	 Alerta.mostrarError("Error de Registro", "El email ya está registrado.");
//            return;
//        }

        // Crear el nuevo lector y lo guarda en la base de datos
        Lector lector = new Lector(0,0,usernameComplet, direccion, telefono, email,password);
        LectorDAO registro = new LectorDAO();
        
        registro.crearLector(lector);
        
        Alerta.mostrarInformacion("Registro Exitoso", "Usuario registrado correctamente.");
        try {
            App.setRoot("iniciarsesion");
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
}