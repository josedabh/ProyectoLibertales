 package libreria.LiberTales;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardsLibros {
	
	@FXML
	private ImageView libroImage;
	@FXML
    private Hyperlink nombreLink;
    
    public void setDatos(String rutaImage,String nombre) {
    	Image image = new Image(rutaImage);
    	libroImage.setImage(image);
        nombreLink.setText(nombre);
    }
    
    @FXML
    private void switchTodetallesLibros() throws IOException {
		App.setRoot("detalleslibros");
	}
}
