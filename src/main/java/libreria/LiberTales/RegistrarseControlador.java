package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.Lector;
import dto.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private void switchToInicioSesion() throws IOException {
        App.setRoot("iniciarsesion");
    }
}