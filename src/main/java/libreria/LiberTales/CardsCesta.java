package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import dao.LibroDAO;
import dto.Cesta;
import dto.Libro;

public class CardsCesta {

    @FXML
    private ImageView imageView;
    
    @FXML
    private Label tituloLabel;
    
    private int idLibro;
    
    // Este método se utiliza para establecer los datos del libro
    public void setDatos(Cesta cesta) {
    	LibroDAO libroDao = new LibroDAO();
    	Libro libro = libroDao.obtenerLibroPorId(cesta.getIdLibro());
        Image image = new Image(libro.getRutaImagen());
        imageView.setImage(image);
        tituloLabel.setText(libro.getTitulo());
    }
    
    

}
