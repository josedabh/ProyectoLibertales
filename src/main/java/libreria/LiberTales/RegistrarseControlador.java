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
            Alerta.mostrarError("Error de Registro", "Por favor, completa todos los campos.");
            return;
        }

        // Verificar que las contraseñas coincidan
        if (!contrasena.equals(confirmarContrasena)) {
            Alerta.mostrarError("Error de Registro", "Las contraseñas no coinciden.");
            return;
        }

        // Validar que el correo no esté registrado ya en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();

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
}
