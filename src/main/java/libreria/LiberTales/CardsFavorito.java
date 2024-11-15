package libreria.LiberTales;

import dao.LibroDAO;
import dto.Favorito;
import dto.Libro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardsFavorito {
	   @FXML
	    private ImageView imageView;
	    
	    @FXML
	    private Label tituloLabel;
	    
	    private int idLibro;
	    
	    // Este método se utiliza para establecer los datos del libro
	    public void setDatos(Favorito favorito) {
	    	LibroDAO libroDao = new LibroDAO();
	    	Libro libro = libroDao.obtenerLibroPorId(favorito.getIdLibro());
	        Image image = new Image(libro.getRutaImagen());
	        imageView.setImage(image);
	        tituloLabel.setText(libro.getTitulo());
	    }
}
