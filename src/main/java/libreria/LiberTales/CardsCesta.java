package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.CestaDAO;
import dao.CompraDAO;
import dao.LibroDAO;
import dto.Cesta;
import dto.Libro;
import dto.SesionUsuario;

public class CardsCesta {

    @FXML
    private ImageView imageView;
    
    @FXML
    private Label tituloLabel;
    @FXML
    private Label precioLabel;
    
    private Libro libro;
    
    // Este método se utiliza para establecer los datos del libro
    public void setDatos(Cesta cesta) {
    	LibroDAO libroDao = new LibroDAO();
    	Libro libro = libroDao.obtenerLibroPorId(cesta.getIdLibro());
        Image image = new Image(libro.getRutaImagen());
        imageView.setImage(image);
        tituloLabel.setText(libro.getTitulo());
        precioLabel.setText(String.format("€ %.2f", libro.getPrecioCompra())); 
        setLibro(libro);
    }
    
    @FXML
    private void switchToComprarLibro() throws IOException {
        CompraDAO comprarDAO = new CompraDAO();
    	Libro libro = getLibro();
        try {
        
            // Obtener la fecha actual como LocalDate (sin la hora)
            LocalDate localDate = LocalDate.now(); // Solo la fecha actual

            // Convertir LocalDate a java.sql.Date para usar con la base de datos
            Date fechaCompra = java.sql.Date.valueOf(localDate);
            
            // Realizar la compra
            comprarDAO.comprarLibro(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro(), fechaCompra);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	@FXML
	private void eliminarDeLaCesta() throws SQLException {
	    int idLector = SesionUsuario.getInstancia().getIdLector();
		int idLibro = getLibro().getId_libro();

		// Llamar al método para eliminar el libro de la cesta
		CestaDAO cestaDAO = new CestaDAO();
		cestaDAO.eliminarDeLaCesta(idLector, idLibro);

		// Mensaje para confirmar eliminación o actualizar la UI según sea necesario
		System.out.println("El libro ha sido eliminado de la cesta.");
	}


}
