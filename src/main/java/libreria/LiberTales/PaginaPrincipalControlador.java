package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Conexion.ConexionBD;
import dao.LibroDAO;
import dto.Busqueda;
import dto.Libro;
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
	private VBox contenedorCartas;
	
	@FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	@FXML
	private void switchToCesta() throws IOException {
	    App.setRoot("cesta"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
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


	public void initialize() {
		cargarCartas();
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
                controladorCarta.setDatos(libro.getRutaImagen(), libro.getTitulo());

                // Agregar la carta al contenedor
                tilePaneCartas.getChildren().add(carta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
