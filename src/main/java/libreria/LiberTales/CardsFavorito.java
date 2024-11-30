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
	
		// Botones y campos de la interfaz grafica 'cardsFavorito.fxml'
	
	   @FXML
	    private ImageView libroImagen;
	    
	    @FXML
	    private Label titulo;
	    
	    @FXML
	    private int idLibro;
	    
	    @FXML
	    private Libro libro;
	    
	    // Este metodo se utiliza para establecer los datos del libro
	    public void establecerDatos(Favorito favorito) {
	    	// DAO para interactuar con la bbdd
	    	LibroDAO libroDao = new LibroDAO();
	    	// Obtenemos el libro por su id
	    	Libro libro = libroDao.obtenerLibroPorId(favorito.getIdLibro());
	    	// Cargamos la imagen
	        Image image = new Image(libro.getRutaImagen());
	        libroImagen.setImage(image);
	        // Establecemos el titulo del libro
	        titulo.setText(libro.getTitulo());
	        // Guardamos el libro
	        setLibro(libro);
	    }
	    
	 // Metodo para añadir un libro a la cesta
	    @FXML
	    private void anadirLibroACesta() throws IOException {
	        // Obtenemos el libro actual
	        Libro libro = getLibro();
	        // DAO para manejar favoritos y cesta
	        FavoritoDAO favoritoDAO = new FavoritoDAO();
	        // Metodo de agregar el libro a la cesta
	        favoritoDAO.agregarALaCesta(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());//Quitar el id de libro
            // Muestra un mensaje al usuario de que se ha añadido a la cesta el libro correctamente
	        Alerta.mostrarInformacion("Libro añadido a la cesta", "¡El libro ha sido añadido a la cesta con éxito!");

	    }
	    
	    // Metodo getter y setter 
	    public Libro getLibro() {
	    	// Devuelve el libro actual
			return libro;
		}

		public void setLibro(Libro libro) {
			// Establece el libro actual
			this.libro = libro;
		}
		
	    // Metodo para eliminar un libro de favorito
	    @FXML
	    private void EliminarLibroDeFavorito() throws IOException {
	        // Obtenemos el libro actual
	        Libro libro = getLibro();
	        // DAO para manejar favoritos
	        FavoritoDAO favoritoDAO = new FavoritoDAO(); 
	        // Eliminamos el libro de favoritos
	        favoritoDAO.eliminarDeFavorito(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());
            // Muestra una mensaje de alerta al usuario para que vea que se ha eliminado el libro de la cesta
	        Alerta.mostrarInformacion("Libro eliminado de favorito", "¡El libro ha sido eliminado de favoritos!");
	    }
}
