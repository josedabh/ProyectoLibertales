package libreria.LiberTales;

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
    
    // Este método se invoca para establecer los detalles del libro
    public void setDetalles(String rutaImagen, String titulo, String sinopsis) {
        Image image = new Image(rutaImagen);
        detalleImage.setImage(image);
        tituloLabel.setText(titulo);
        sinopsisText.setText(sinopsis);
    }

}
