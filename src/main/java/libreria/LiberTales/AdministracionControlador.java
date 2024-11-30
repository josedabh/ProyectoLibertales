package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdministracionControlador {

    @FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button botonVolverAtras;
    @FXML
    private Button botonAdministrarLibros;
    @FXML
    private Button botonAdminAlquiler;
    @FXML
    private Button botonCerrarSesion;

    @FXML
    public void iniciar() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        Integer idAdmin = SesionAdmin.getInstancia().getIdAdmin();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
        } else if (idAdmin != null) {
            System.out.println("ID del administrador en la sesión: " + idAdmin);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
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
    private void cambiarAAdministrarLibros() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("adminlibros.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonUsuario.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Administración de libros");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void cambiarAHistorialAlquiler() throws IOException {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("adminalquiler.fxml"));
            Parent raiz = cargador.load();
            Stage escenario = (Stage) botonAdminAlquiler.getScene().getWindow();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Historial Alquiler");
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesion() throws IOException {
        // Redirigir al usuario a la pantalla de inicio
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
        Parent raiz = cargador.load();
        Stage escenario = (Stage) botonCerrarSesion.getScene().getWindow();
        escenario.setScene(new Scene(raiz));
        escenario.setTitle("Página Principal");
        escenario.show();

        // Limpiar la sesión del administrador
        SesionAdmin.getInstancia().cerrarSesion();
    }
    
    @FXML
    private void volverAPaginaPrincipal() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) botonVolverAtras.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Página principal");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}
