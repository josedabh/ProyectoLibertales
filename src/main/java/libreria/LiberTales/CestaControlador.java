package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import dao.CestaDAO;
import dto.Cesta;

import java.io.IOException;
import java.util.List;

public class CestaControlador {

    @FXML
    private VBox cestaVBox; // VBox donde se mostrarán los libros en la cesta

    private CestaDAO cestaDAO;

    // Constructor simple
    public CestaControlador() {
        cestaDAO = new CestaDAO(); // Inicializamos el objeto DAO
    }

    @FXML
    public void initialize() {
        // Cargar los libros de la cesta cuando la vista se inicializa
        cargarLibrosEnCesta();
    }

    // Método para cargar los libros en la cesta
    private void cargarLibrosEnCesta() {
        List<Cesta> librosEnCesta = cestaDAO.obtenerCesta(1); // Usamos un idLector ejemplo (1)
        cestaVBox.getChildren().clear(); // Limpiamos la VBox para evitar duplicados

        for (Cesta itemCesta : librosEnCesta) {
            // Para cada libro en la cesta, creamos un HBox con los botones y detalles
            HBox libroHBox = new HBox(10);
            libroHBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10; -fx-border-color: #D3D3D3; -fx-border-radius: 5;");

            // Simplemente mostramos el ID del libro (esto puede mejorarse con el título real del libro)
            Label tituloLabel = new Label("Libro ID: " + itemCesta.getIdLibro()); 
            tituloLabel.setStyle("-fx-font-size: 16px;");

            // Creamos los botones para cada acción (Comprar, Alquilar, Eliminar)
            Button comprarButton = new Button("Comprar");
            Button alquilarButton = new Button("Alquilar");
            Button eliminarButton = new Button("Eliminar");

            // Definimos las acciones de los botones
            comprarButton.setOnAction(e -> comprarLibro(itemCesta));
            alquilarButton.setOnAction(e -> alquilarLibro(itemCesta));
            eliminarButton.setOnAction(e -> eliminarLibroDeLaCesta(itemCesta));

            // Añadimos los botones al HBox de acciones
            HBox accionesHBox = new HBox(10, comprarButton, alquilarButton, eliminarButton);

            // Añadimos la imagen y los botones de acción al HBox principal
            libroHBox.getChildren().addAll(tituloLabel, accionesHBox);

            // Finalmente añadimos el HBox a la VBox de la cesta
            cestaVBox.getChildren().add(libroHBox);
        }
    }

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
    private void switchToPagina() throws IOException {
        App.setRoot("paginaPrincipal");
    }
    // Método para comprar un libro
    private void comprarLibro(Cesta itemCesta) {
        cestaDAO.agregarALaCesta(itemCesta.getIdLector(), itemCesta.getIdLibro());
        System.out.println("Libro comprado: ID " + itemCesta.getIdLibro());
        cargarLibrosEnCesta(); // Refresca la lista después de la acción
    }

    // Método para alquilar un libro
    private void alquilarLibro(Cesta itemCesta) {
        cestaDAO.alquilarLibro(itemCesta.getIdLector(), itemCesta.getIdLibro());
        System.out.println("Libro alquilado: ID " + itemCesta.getIdLibro());
        cargarLibrosEnCesta(); // Refresca la lista después de la acción
    }

    // Método para eliminar un libro de la cesta
    private void eliminarLibroDeLaCesta(Cesta itemCesta) {
        cestaDAO.eliminarDeLaCesta(itemCesta.getIdLector(), itemCesta.getIdLibro());
        System.out.println("Libro eliminado de la cesta: ID " + itemCesta.getIdLibro());
        cargarLibrosEnCesta(); // Refresca la lista después de la acción
    }
}

