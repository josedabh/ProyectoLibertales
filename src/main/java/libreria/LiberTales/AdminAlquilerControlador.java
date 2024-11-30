package libreria.LiberTales;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Alertas.Alerta;
import dao.AlquilerDAO;
import dto.HistorialAlquiler;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminAlquilerControlador implements Initializable{
	
	@FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button botonVolverAtras;
    @FXML
    private TableView<HistorialAlquiler> tablaAlquiler;
    @FXML
    private TableColumn colLector;
    @FXML
    private TableColumn colLibro;
    @FXML
    private TableColumn colFecAlta;
    @FXML
    private TableColumn colFecDev;
    
    private ObservableList<HistorialAlquiler> observableAlquiler;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	AlquilerDAO alquilerDao = new AlquilerDAO();
    	List<HistorialAlquiler> histAlquiler = alquilerDao.obtenerListaDescendente();
    	 // Convierte la lista a un ObservableList
    	observableAlquiler = FXCollections.observableArrayList(histAlquiler);
    	this.tablaAlquiler.setItems(observableAlquiler);

        // Asigno las columnas con los atributos del modelo
        this.colLector.setCellValueFactory(new PropertyValueFactory("nomLector")); //Nombre del atributo del modelo
        this.colLibro.setCellValueFactory(new PropertyValueFactory("tituloLibro"));
        this.colFecAlta.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        this.colFecDev.setCellValueFactory(new PropertyValueFactory("fechaLimite"));
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
    private void volverAtras() throws IOException {
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
