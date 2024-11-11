 package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardsLibros {
	
	@FXML
	private ImageView libroImage;
	@FXML
    private Label nombreLabel;
    
    @FXML
    private Label descripcionLabel;
    
    public void setDatos(String rutaImage,String nombre, String descripcion) {
    	Image image = new Image(rutaImage);
    	libroImage.setImage(image);
        nombreLabel.setText(nombre);
        descripcionLabel.setText(descripcion);

    }
}
