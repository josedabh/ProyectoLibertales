package libreria.LiberTales;

import dao.AlquilerDAO;
import dao.LibroDAO;
import dto.Alquiler;
import dto.Cesta;
import dto.Libro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardsAlquiler {

	// Botones y campos de la interfaz grafica 'cardsAlquiler.fxml'
	
    @FXML
    private ImageView libroImagen;
    
    @FXML
    private Label tituloLabel;
    
    @FXML
    private Label fechaInspiracion;
    
    @FXML
    private Label fechaExpiracion;
    
    @FXML
    private Libro libro;
    
    // Este método se utiliza para establecer los datos del libro
    public void establecerDatos(Alquiler itemCesta) {
        // DAO para interactuar con la bbdd
        LibroDAO libroDao = new LibroDAO();

        // Obtenemos el libro por su ID
        Libro libro = libroDao.obtenerLibroPorId(itemCesta.getIdLibro());

        // Cargamos la imagen del libro
        Image image = new Image(libro.getRutaImagen());
        libroImagen.setImage(image);

        // Establecemos el título del libro
        tituloLabel.setText(libro.getTitulo());
        // Establecemos la fecha de expiración del alquiler
        if (itemCesta.getFechaLimite() != null) {
            fechaInspiracion.setText(itemCesta.getFechaLimite().toString()); // Convertimos LocalDate a String
        } else {
            fechaInspiracion.setText("Sin fecha de expiración");
        }

        // Guardamos el libro
        setLibro(libro);
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
}
