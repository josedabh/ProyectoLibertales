package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Conexion.ConexionBD;
import dao.LibroDAO;
import dto.Libro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class PaginaPrincipalControlador {
	
	@FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TilePane tilePaneCartas;
    
	@FXML
	private HBox contenedorCartas;
	
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

	public void initialize() {
		cargarCartas();
	}
	
	private void cargarCartas() {
		try {
			LibroDAO libroDAO = new LibroDAO();
            List<Libro> listaLibros = libroDAO.obtenerTodosLosLibros();
            
            for(Libro libro: listaLibros) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                HBox carta = loader.load();

                // Obtener el controlador de la carta y pasar los datos
                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro.getRutaImagen(), libro.getTitulo(),libro.getSinopsis());

                // Agregar la carta al contenedor
                tilePaneCartas.getChildren().add(carta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
