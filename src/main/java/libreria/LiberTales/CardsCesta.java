package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import Alertas.Alerta;
import dao.CestaDAO;
import dao.LibroDAO;
import dto.Cesta;
import dto.Libro;
import dto.SesionUsuario;

public class CardsCesta {

	// Botones y campos de la interfaz grafica 'cardsCesta.fxml'
	
    @FXML
    private ImageView libroImagen;
    
    @FXML
    private Label tituloLabel;
    
    @FXML
    private Label precioLabel;
    
    @FXML
    private Libro libro;
    
    // Este método se utiliza para establecer los datos del libro
    public void establecerDatos(Cesta cesta) {
    	// DAO para interactuar con la bbdd
    	LibroDAO libroDao = new LibroDAO();
    	// Obtenemos el libro por su id
    	Libro libro = libroDao.obtenerLibroPorId(cesta.getIdLibro());
    	// Cargamos la imagen
        Image image = new Image(libro.getRutaImagen());
        libroImagen.setImage(image);
        // Establecemos el titulo del libro
        tituloLabel.setText(libro.getTitulo());
        // Establecemos el precio del libro
        precioLabel.setText(String.format("€ %.2f", libro.getPrecioCompra())); 
        // Guardamos el libro
        setLibro(libro);
    }
    
    // Metodo para comprar libro
    @FXML
    private void comprarLibro() throws IOException {
    	// Instancia de CestaDAO
        CestaDAO cestaDAO = new CestaDAO();
        // Obtenemos el objeto libro que el usuario desea comprar
    	Libro libro = getLibro();
        try {
        
            // Obtener la fecha actual como LocalDate (sin la hora)
            LocalDate localDate = LocalDate.now(); // Solo la fecha actual

            // Convertir LocalDate a java.sql.Date para usar con la base de datos
            Date fechaCompra = java.sql.Date.valueOf(localDate);
            
            // Realizar la compra
            cestaDAO.comprarLibro(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro(), fechaCompra);
            
            // Muestra una alerta al usuario de que se ha realizado la compra de ese libro al que le ha dado a comprar
            Alerta.mostrarInformacion("Compra realizada", "¡El libro ha sido comprado con éxito!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
	
	// Metodo de eliminar un libro de la cesta
	@FXML
	private void eliminarDeLaCesta() throws SQLException {
		
		// Obtiene el id del lector 
	    int idLector = SesionUsuario.getInstancia().getIdLector();
	    // Obtiene el id del libro
		int idLibro = getLibro().getId_libro();

		// Llamar al método para eliminar el libro de la cesta
		CestaDAO cestaDAO = new CestaDAO();
		cestaDAO.eliminarDeLaCesta(idLector, idLibro);
		// Muestra una alerta al usuario de que el libro se ha eliminado de la cesta
        Alerta.mostrarInformacion("Libro eliminado", "¡El libro se ha eliminado de la cesta con éxito!");
	}


}
