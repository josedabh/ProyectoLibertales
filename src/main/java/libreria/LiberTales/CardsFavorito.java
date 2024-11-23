package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.CestaDAO;
import dao.FavoritoDAO;
import dao.LibroDAO;
import dto.Favorito;
import dto.Libro;
import dto.SesionUsuario;
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
	    
	    private Libro libro;
	    
	    // Este método se utiliza para establecer los datos del libro
	    public void setDatos(Favorito favorito) {
	    	LibroDAO libroDao = new LibroDAO();
	    	Libro libro = libroDao.obtenerLibroPorId(favorito.getIdLibro());
	        Image image = new Image(libro.getRutaImagen());
	        imageView.setImage(image);
	        tituloLabel.setText(libro.getTitulo());
	        setLibro(libro);
	    }
	    
	 // Método que se ejecuta al hacer clic en el botón de cesta
	    @FXML
	    private void switchToAnadiraFavorito() throws IOException {
	        // Agregar el libro a la cesta
	        Libro libro = getLibro();
	        CestaDAO cestaDAO = new CestaDAO();
	        // Obtener el libro que se está viendo
	        cestaDAO.agregarACesta(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());//Quitar el id de libro
            Alerta.mostrarInformacion("Libro añadido a la cesta", "¡El libro ha sido añadido a la cesta con éxito!");

	    }
	    public Libro getLibro() {
			return libro;
		}

		public void setLibro(Libro libro) {
			this.libro = libro;
		}
	    // Método que se ejecuta al hacer clic en el botón de favorito
	    @FXML
	    private void switchToEliminarDeFavorito() throws IOException {
	        // Agregar el libro a la cesta
	        Libro libro = getLibro();
	        FavoritoDAO favoritoDAO = new FavoritoDAO();
	        // Obtener el libro que se está viendo
	        favoritoDAO.eliminarDeFavorito(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());
            Alerta.mostrarInformacion("Libro eliminado de favorito", "¡El libro ha sido eliminado de favoritos!");

	    }
}
