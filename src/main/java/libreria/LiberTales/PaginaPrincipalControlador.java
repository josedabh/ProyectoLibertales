package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Conexion.ConexionBD;
import dao.LibroDAO;
import dto.Libro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PaginaPrincipalControlador {
	
	@FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TilePane tilePaneCartas;
    
	@FXML
	private VBox contenedorCartas;
	
	@FXML
    private TextField searchField; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button searchButton; 
    
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

    public void initialize() {
    	cargarCartas();
        searchButton.setOnAction(event -> buscarLibros()); // Asocia el botón al método de búsqueda
        cargarLibros(null); // Carga todos los libros al iniciar la aplicación
    }
    private void cargarLibros(String searchText) {
        tilePaneCartas.getChildren().clear(); // Limpia el contenedor antes de agregar resultados

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros;
        
        if (searchText == null || searchText.isEmpty()) {
            listaLibros = libroDAO.obtenerTodosLosLibros(); // Obtiene todos los libros si no hay texto de búsqueda
        } else {
            listaLibros = libroDAO.buscarLibrosPorTitulo(searchText); // Obtiene libros filtrados por el título
        }
        
        for (Libro libro : listaLibros) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();
                
                // Obtener el controlador de la carta y establecer los datos del libro
                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro.getRutaImagen(), libro.getTitulo());

                tilePaneCartas.getChildren().add(carta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método que se ejecuta al hacer clic en el botón de búsqueda
    public void buscarLibros() {
        String searchText = searchField.getText(); // Obtiene el texto del campo de búsqueda
        cargarLibros(searchText); // Llama a cargarLibros con el texto de búsqueda
    }

}
