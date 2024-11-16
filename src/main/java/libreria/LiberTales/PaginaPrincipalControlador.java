package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dao.LectorDAO;
import dao.LibroDAO;
import dto.Busqueda;
import dto.Lector;
import dto.Libro;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaginaPrincipalControlador {
	
	@FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TilePane tilePaneCartas;
    
    @FXML
    private TextField searchField; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button searchButton; 
    
    @FXML
    private Button cartButton;
    
	@FXML
	private VBox contenedorCartas;
	
	public Lector lector;
	
	
	public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        System.out.println("Funciona" + idLector);
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
        cargarCartas();
	}
	
	@FXML
	private void switchtoLogin() throws IOException {
		if(SesionUsuario.getInstancia().getIdLector()==null) {
			App.setRoot("iniciarsesion");
		} else {
			App.setRoot("modificarUsuario");
		}
	}
	
	@FXML
	private void switchToCesta() throws IOException {
		
		if(SesionUsuario.getInstancia().getIdLector()==null) {
			
			Alerta.mostrarError("Error al cargar cesta", "Primero, hay que iniciar sesion");
		} else {
			// Crear una instancia de FXMLLoader y cargar el archivo FXML
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
		    Parent root = loader.load();
		    
		    // Obtener el controlador de la vista cargada
		    CestaControlador controller = loader.getController();
		    
		    // Mostrar la nueva escena
		    Stage stage = (Stage) cartButton.getScene().getWindow();
		    stage.setScene(new Scene(root));
		    stage.show();
		}
	    
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito");
	}
	
	@FXML
	private void switchToBusqueda() throws IOException {
	    // Crear una instancia de FXMLLoader y cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("busquedalibros.fxml"));
	    Parent root = loader.load();
	    
	    // Obtener el controlador de la vista cargada
	    BusquedaLibrosControlador controller = loader.getController();
	    
	    // Crear una instancia de Busqueda con el texto de búsqueda actual
	    Busqueda busqueda = new Busqueda(searchField.getText());
	    
	    // Pasar la instancia de Busqueda al controlador de la vista de búsqueda
	    controller.setBusqueda(busqueda);
	    
	    // Mostrar la nueva escena
	    Stage stage = (Stage) searchButton.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	
	private void cargarCartas() {
		try {
			LibroDAO libroDAO = new LibroDAO();
            List<Libro> listaLibros = libroDAO.obtenerTodosLosLibros();
            
            for(Libro libro: listaLibros) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();

                // Obtener el controlador de la carta y pasar los datos
                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro);

                // Agregar la carta al contenedor
                tilePaneCartas.getChildren().add(carta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
