package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class CardsLibros {

    @FXML
    private ImageView libroImage;
    
    @FXML
    private Hyperlink nombreLink;
    
    private String rutaImagen;
    private String titulo;
    private String sinopsis;

    // Este método se utiliza para establecer los datos del libro
    public void setDatos(String rutaImage, String nombre, String sinopsis) {
        this.rutaImagen = rutaImage;
        this.titulo = nombre;
        this.sinopsis = sinopsis;
        
        Image image = new Image(rutaImage);
        libroImage.setImage(image);
        nombreLink.setText(nombre);
    }

    // Este método es llamado cuando el usuario hace clic en el enlace
    @FXML

    private void switchTodetallesLibros() throws IOException {
        // Cargar la vista de detalles y obtener el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detalleslibros.fxml"));
        Parent root = loader.load();
        DetallesLibrosControlador controladorDetalles = loader.getController();
        
        // Pasar los datos del libro al controlador de la vista de detalles
        controladorDetalles.setDetalles(rutaImagen, titulo, sinopsis);
        
        // Cambiar la vista principal pasando el nombre del archivo FXML
        App.setRoot("detalleslibros");  // Usamos solo el nombre del archivo
    }



}

