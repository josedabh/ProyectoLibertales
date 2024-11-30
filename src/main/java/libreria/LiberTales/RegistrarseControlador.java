package libreria.LiberTales;

import java.io.IOException;
import Alertas.Alerta;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.Lector;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrarseControlador {

    @FXML
    private TextField campoNombreUsuarioCompleto;
    @FXML
    private TextField campoCorreoElectronico;
    @FXML
    private TextField campoTelefono;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private PasswordField campoConfirmarContrasena;
    @FXML
    private TextArea campoDireccion;
    @FXML
    private Button botonRegistrar;
    @FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button botonVolver;
    @FXML
    private Label errorNombreCompleto;
    @FXML
    private Label errorDireccion;
    @FXML
    private Label errorTelefono;
    @FXML
    private Label errorCorreoElectronico;
    @FXML
    private Label errorContrasena;
    @FXML 
    private Label errorConfirmarContrasena;
    @FXML
	private Button alquilerboton; 
    
    public void initialize() {
    	// Listener para validar el campo de nombre completo en tiempo real
        campoNombreUsuarioCompleto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                campoNombreUsuarioCompleto.setStyle("-fx-border-color: red;");
                errorNombreCompleto.setText("El nombre no puede estar vacío");
            } else {
                campoNombreUsuarioCompleto.setStyle(null); // Limpia estilos de error
                errorNombreCompleto.setText(""); // Limpia el mensaje
            }
        });
        
     // Listener para validar el campo de dirección en tiempo real
        campoDireccion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                campoDireccion.setStyle("-fx-border-color: red;");
                errorDireccion.setText("La dirección no puede estar vacía");
            } else {
                campoDireccion.setStyle(null); // Limpia estilos de error
                errorDireccion.setText(""); // Limpia el mensaje
            }
        });
        
     // Listener para validar el campo de teléfono en tiempo real
        campoTelefono.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                campoTelefono.setStyle("-fx-border-color: red;");
                errorTelefono.setText("El teléfono debe tener 9 números");
            } else {
                campoTelefono.setStyle(null); // Limpia estilos de error
                errorTelefono.setText(""); // Limpia el mensaje
            }
        });
        
     // Listener para validar el campo de correo en tiempo real
        campoCorreoElectronico.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!esCorreoValido(newValue)) {
                campoCorreoElectronico.setStyle("-fx-border-color: red;");
                errorCorreoElectronico.setText("Por favor, introduce un correo válido");
            } else {
                campoCorreoElectronico.setStyle(null); // Limpia estilos de error
                errorCorreoElectronico.setText(""); // Limpia el mensaje
            }
        });
        
     // Listener para validar el campo de contraseña en tiempo real
        campoContrasena.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                campoContrasena.setStyle("-fx-border-color: red;");
                errorContrasena.setText("La contraseña no puede estar vacía");
            } else {
                campoContrasena.setStyle(null); // Limpia estilos de error
                errorContrasena.setText(""); // Limpia el mensaje
            }
        });
        
     // Listener para validar que los valores de los campos Contrasena y ConfirmarContrasena son iguales
        campoConfirmarContrasena.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(campoContrasena.getText())) {
                campoConfirmarContrasena.setStyle("-fx-border-color: red;");
                errorConfirmarContrasena.setText("Las contraseñas no coinciden");
            } else {
                campoConfirmarContrasena.setStyle(null); // Limpia estilos de error
                errorConfirmarContrasena.setText(""); // Limpia el mensaje
            }
        });
    }

    // Método que se ejecuta cuando el usuario hace clic en "Registrar"
    @FXML
    private void registrarUsuario(ActionEvent evento) {
        String nombreUsuarioCompleto = campoNombreUsuarioCompleto.getText();
        String direccion = campoDireccion.getText();
        String correoElectronico = campoCorreoElectronico.getText().trim();
        String contrasena = campoContrasena.getText();
        String confirmarContrasena = campoConfirmarContrasena.getText();
        String telefono = campoTelefono.getText();
        
        // Validar que los campos no estén vacíos
        if (correoElectronico.isEmpty() || contrasena.isEmpty() || confirmarContrasena.isEmpty() || 
                direccion.isEmpty() || nombreUsuarioCompleto.isEmpty() || telefono.isEmpty()) {
            errorNombreCompleto.setText("Por favor, completa todos los campos");
            return;
        }

        // Verificar que las contraseñas coincidan
        if (!contrasena.equals(confirmarContrasena)) {
            Alerta.mostrarError("Error de Registro", "Las contraseñas no coinciden.");
            return;
        }

        // Crear el nuevo lector y guardarlo en la base de datos
        Lector lector = new Lector(0, 0, nombreUsuarioCompleto, direccion, telefono, correoElectronico, contrasena);
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
    private void cambiarAInicioSesion() throws IOException {
        if (SesionUsuario.getInstancia().getIdLector() == null &&
                SesionAdmin.getInstancia().getIdAdmin() == null) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonUsuario.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Iniciar sesión");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (SesionAdmin.getInstancia().getIdAdmin() != null) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("administracion.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonUsuario.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Administración");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("modificarusuario.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonUsuario.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Modificar usuario");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void cambiarACesta() throws IOException {
        if (SesionUsuario.getInstancia().getIdLector() != null) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("cesta.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonCesta.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Cesta");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alerta.mostrarError("Error al ir a la cesta", "Primero, tienes que iniciar sesión");
        }
    }

    @FXML
    private void cambiarAFavoritos() throws IOException {
        if (SesionUsuario.getInstancia().getIdLector() != null) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("favorito.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonFavoritos.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Favoritos");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
        }
    }

    @FXML
    private void volverAInicioSesion() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonVolver.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Iniciar sesión");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 // Metodo para volver a la pagina de libros alquilados
   @FXML
   	private void ventanaAlquiler() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   				// Carga la vista de la pagina principal 'alquiler.fxml'
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("alquiler.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) alquilerboton.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Alquiler");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir a alquiler", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
    
    private boolean esCorreoValido(String email) {
		// Expresión regular básica para validar correos electrónicos
		return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
	}
    
    private boolean esTelefonoValido(String telefono) {
        // Expresión regular para validar que el teléfono tiene exactamente 9 dígitos
        return telefono.matches("^\\d{9}$");
    }
}
