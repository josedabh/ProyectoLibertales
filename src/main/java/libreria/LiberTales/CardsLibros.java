package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import dto.Libro;

public class CardsLibros {

    @FXML
    private ImageView libroImage;
    
    @FXML
    private Label nombreLabel;

    @FXML
    private Button detallesButton;
    
    private Libro infoLibro;
    
    // Este método se utiliza para establecer los datos del libro
    public void setDatos(Libro libro) {
        Image image = new Image(libro.getRutaImagen());
        libroImage.setImage(image);
        nombreLabel.setText(libro.getTitulo());
        this.infoLibro = libro;
    }

    // Este método es llamado cuando el usuario hace clic en el enlace
    @FXML
    private void switchTodetallesLibros() throws IOException {
    	// Crear una instancia de FXMLLoader y cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("detalleslibro.fxml"));
	    Parent root = loader.load();
	    
	    // Obtener el controlador de la vista cargada
	    DetallesLibrosControlador controller = loader.getController();
	    
	    // Crear una instancia de Busqueda con el texto de búsqueda actual
	    Libro infoLibro = this.infoLibro;
	    
	    // Pasar la instancia de Busqueda al controlador de la vista de búsqueda
	    controller.setDetalles(infoLibro);
	    
	    // Mostrar la nueva escena
	    Stage stage = (Stage) detallesButton.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Detalles de " + infoLibro.getTitulo());
	    stage.show();
    }

    public void setInfoLibro(Libro infoLibro) {
		this.infoLibro = infoLibro;
	}

}
