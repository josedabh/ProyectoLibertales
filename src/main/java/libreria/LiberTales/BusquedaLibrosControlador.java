package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import dao.LibroDAO;
import dto.Busqueda;
import dto.Libro;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class BusquedaLibrosControlador {

    @FXML
    private ScrollPane scrollBusqueda;
    
    @FXML
    private TilePane tilePaneBusqueda;
    
    @FXML
    private TextField searchField; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button searchButton; 
    
    private Busqueda busqueda;
    
	@FXML
	private void switchToBusqueda() throws IOException {
//	    // Crear una instancia de FXMLLoader y cargar el archivo FXML
//	    FXMLLoader loader = new FXMLLoader(getClass().getResource("busquedalibros.fxml"));
//	    Parent root = loader.load();
//	    
//	    // Obtener el controlador de la vista cargada
//	    BusquedaLibrosControlador controller = loader.getController();
//	    
//	    // Crear una instancia de Busqueda con el texto de búsqueda actual
//	    Busqueda busqueda = new Busqueda(searchField.getText());
//	    
//	    // Pasar la instancia de Busqueda al controlador de la vista de búsqueda
//	    controller.setBusqueda(busqueda);
//	    
//	    // Mostrar la nueva escena
//	    Stage stage = (Stage) searchButton.getScene().getWindow();
//	    stage.setScene(new Scene(root));
//	    stage.show();
		System.out.println("Funciona");
	}


    public void initialize() {
        // Configura el campo de búsqueda inicial
        if (busqueda != null) {
            this.searchField.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }

        // Agrega un listener al campo de búsqueda para actualizar al escribir
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            busqueda.setBuscar(newValue);
            cargarLibros(newValue);
        });
    }

    private void cargarLibros(String searchText) {
        tilePaneBusqueda.getChildren().clear();

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros = (searchText == null || searchText.isEmpty())
            ? libroDAO.obtenerTodosLosLibros()
            : libroDAO.buscarLibrosPorTitulo(searchText);

        for (Libro libro : listaLibros) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();

                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro);

                tilePaneBusqueda.getChildren().add(carta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
        if (searchField != null) {
            searchField.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }
    }
    
    @FXML
	private void switchtoLogin() throws IOException {
         App.setRoot("iniciarsesion");
	}
	
	@FXML
	private void switchToCesta() throws IOException {
	    App.setRoot("cesta"); 
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito"); 
	}
	
	@FXML
    private void switchToPagina() throws IOException {
        App.setRoot("paginaPrincipal");
    }
	
}
