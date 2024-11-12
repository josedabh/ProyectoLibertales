package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class CardsLibros {

    @FXML
    private ImageView libroImage;
    
    @FXML
    private Label nombreLabel;

    // Este método se utiliza para establecer los datos del libro
    public void setDatos(String rutaImage, String nombre) {
        Image image = new Image(rutaImage);
        libroImage.setImage(image);
        nombreLabel.setText(nombre);
    }

    // Este método es llamado cuando el usuario hace clic en el enlace
    @FXML
    private void switchTodetallesLibros() throws IOException {
        App.setRoot("detallesLibro");
    }



}

