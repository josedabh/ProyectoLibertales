package libreria.LiberTales;

import java.io.IOException;
import Alertas.Alerta;
import dao.AdministradorDAO;
import dao.LectorDAO;
import dao.UsuarioDAO;
import dto.SesionAdmin;
import dto.SesionUsuario;
import dto.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class IniciarSesionControlador {

    @FXML
    private ImageView logoImagenVista;
    @FXML
    private Button botonUsuario;
    @FXML
    private Button botonCarrito;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button botonAtras;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private TextField campoCorreo;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Button botonIniciarSesion;
    @FXML
    private Button botonRegistrarse;
    @FXML
    private Button botonAgregar;
    @FXML
    private Hyperlink enlaceOlvidarContrasena;
    @FXML
    private Label etiquetaPiePagina;
    @FXML
    private Label errorCorreo;
    @FXML
    private Label errorContrasena;
    @FXML
    private Button botonAlquiler;

    public void inicializar() {
        // Listener para validar el campo de correo en tiempo real
        campoCorreo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!esCorreoValido(newValue)) {
                campoCorreo.setStyle("-fx-border-color: red;");
                errorCorreo.setText("Introduce un correo válido");
            } else {
                campoCorreo.setStyle(null); // Limpia estilos de error
                errorCorreo.setText(""); // Limpia el mensaje
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
    }

    @FXML
    public void comprobarUsuario(ActionEvent evento) {
        String correo = campoCorreo.getText();
        String contrasena = campoContrasena.getText();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LectorDAO lectorDAO = new LectorDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            Alerta.mostrarError("Error al iniciar sesión", "Por favor, rellena los campos vacíos");
            return;
        }

        Usuario usuario = usuarioDAO.autenticarUsuario(correo, contrasena);

        if (usuario == null) {
            Alerta.mostrarError("Error al iniciar sesión", "Correo o contraseña incorrectos.");
        } else if (usuario.getTipo().equals("administrador")) {
            // Guarda el idLector en la sesión
            SesionAdmin.getInstancia().setIdAdmin(administradorDAO.buscarAdminPorId(usuario.getIdUsuario()));
            System.out.println(SesionAdmin.getInstancia().getIdAdmin());
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("administracion.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonFavoritos.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (usuario.getTipo().equals("lector")) {
            // Guarda el idLector en la sesión
            SesionUsuario.getInstancia().setIdLector(lectorDAO.buscarLectorPorId(usuario.getIdUsuario()));
            System.out.println(SesionUsuario.getInstancia().getIdLector());
            // Cambia a la página principal
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonFavoritos.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void cambiarAPaginaPrincipal() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonAtras.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Página principal");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiarARegistrarse() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("registrarse.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonAtras.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Registrarse");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiarAOlvidarContrasena() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("olvidar.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonAtras.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Olvidar contraseña");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiarAIniciarSesion() throws IOException {
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
                Stage escenario = (Stage) botonCarrito.getScene().getWindow();
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
    private void ventanaAlquiler() throws IOException {
        if (SesionUsuario.getInstancia().getIdLector() != null) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("alquiler.fxml"));
                Parent raiz = cargador.load();
                Stage escenario = (Stage) botonAlquiler.getScene().getWindow();
                escenario.setScene(new Scene(raiz));
                escenario.setTitle("Alquiler");
                escenario.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alerta.mostrarError("Error al ir a alquiler", "Primero, tienes que iniciar sesión");
        }
    }

    private boolean esCorreoValido(String correo) {
        // Expresión regular básica para validar correos electrónicos
        return correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }
}
