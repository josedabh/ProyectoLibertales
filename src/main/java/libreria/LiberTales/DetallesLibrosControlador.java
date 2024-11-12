package libreria.LiberTales;

import java.io.IOException;

import dto.Libro;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class DetallesLibrosControlador {
    
    @FXML
    private ImageView detalleImage;
    
    @FXML
    private Label tituloLabel;
    
    @FXML
    private Text sinopsisText;
    
    @FXML
	private void switchtoLogin() throws IOException{
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
    
    // Este método se invoca para establecer los detalles del libro
    public void setDetalles(Libro libro) {
        Image image = new Image(libro.getRutaImagen());
        detalleImage.setImage(image);
        tituloLabel.setText(libro.getTitulo());
        sinopsisText.setText(libro.getSinopsis());
    }

}
