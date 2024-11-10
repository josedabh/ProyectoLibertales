package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.Lector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class RegistrarseControlador{

    @FXML
    private TextField usernameCompletField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private Label telefonoErrorLabel; // Agrega este Label en el controlador
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField verifyPasswordField;
    @FXML
    private TextArea direccionField;
    @FXML
    private Button registerButton;

    private ValidationSupport validationSupport;

    public void initialize() {
        validationSupport = new ValidationSupport();

        // Registrar el validador para el campo teléfono y personalizar la acción en caso de error
        validationSupport.registerValidator(telefonoField, Validator.createRegexValidator(
                "El teléfono debe tener 6 dígitos", "\\d{6}", Severity.ERROR
        ));

        // Escuchar los cambios en el campo de texto para mostrar u ocultar el Label de error
        telefonoField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d{6}")) {
                telefonoErrorLabel.setVisible(false); // Ocultar el error si tiene 6 dígitos
            } else {
                telefonoErrorLabel.setVisible(true); // Mostrar el error si no tiene 6 dígitos
            }
        });
        // Deshabilitar el botón de registro si hay errores
        registerButton.disableProperty().bind(validationSupport.invalidProperty());
    }

    // Método que se ejecuta cuando el usuario hace clic en "Registrar"
    @FXML
    private void registrarUsuario(ActionEvent event) {
        if (validationSupport.isInvalid()) {
            Alerta.mostrarError("Error de Registro", "Corrija los errores en el formulario.");
            return;
        }

        String usernameComplet = usernameCompletField.getText();
        String direccion = direccionField.getText();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String telefono = telefonoField.getText();

        // Validar que el correo no esté registrado ya en la base de datos


        // Crear el nuevo lector y guardarlo en la base de datos
        Lector lector = new Lector(0, 0, usernameComplet, direccion, telefono, email, password);
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
