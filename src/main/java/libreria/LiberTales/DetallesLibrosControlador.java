package libreria.LiberTales;

import java.io.IOException;


import dao.DetallesLibroDAO;
import dto.Libro;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import dto.Libro;
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
    
    private Libro libro;
    
    // Instancia del DAO para manejar las operaciones en la base de datos
    private DetallesLibroDAO detallesLibroDAO;  
    
    public DetallesLibrosControlador() {
        // Inicializar la instancia del DAO
        detallesLibroDAO = new DetallesLibroDAO();
    }
    
    @FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	@FXML
	private void switchToCesta() throws IOException {
	    App.setRoot("cesta");
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito");
	}
    
    // Este método se invoca para establecer los detalles del libro
    public void setDetalles(Libro libro) {
        Image image = new Image(libro.getRutaImagen());
        detalleImage.setImage(image);
        tituloLabel.setText(libro.getTitulo());
        sinopsisText.setText(libro.getSinopsis());
        setLibro(libro);
    }
    
    public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        System.out.println("Funciona" + idLector);
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
	}


    // Método que se ejecuta al hacer clic en el botón de cesta
    @FXML
    private void switchToAlaCesta() throws IOException {
        // Agregar el libro a la cesta
        Libro libro = getLibro();  // Obtener el libro que se está viendo
        detallesLibroDAO.agregarACesta(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());//Quitar el id de libro
        
        // Cambiar la vista a la cesta
        App.setRoot("cesta");
    }
    
    // Método que se ejecuta al hacer clic en el botón de favoritos
    @FXML
    private void switchToaFavorito() throws IOException {
        // Agregar el libro a los favoritos
        Libro libro = getLibro();  // Obtener el libro que se está viendo
        detallesLibroDAO.agregarAFavoritos(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());
        
        // Cambiar la vista a los favoritos
        App.setRoot("favorito");
    }

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
    
    // Método para obtener el libro actual que se está viendo
    // Este es solo un ejemplo, deberías tener un mecanismo para obtener el libro que se está viendo en la interfaz.

}
