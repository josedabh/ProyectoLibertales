package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.CompraDAO;
import dao.FavoritoDAO;
import dao.LibroDAO;
import dto.Cesta;
import dto.Libro;
import dto.SesionUsuario;

public class CardsCesta {

    @FXML
    private ImageView imageView;
    
    @FXML
    private Label tituloLabel;
    
    
    private int idLibro;
    
    private Libro libro;
    // Este método se utiliza para establecer los datos del libro
    public void setDatos(Cesta cesta) {
    	LibroDAO libroDao = new LibroDAO();
    	Libro libro = libroDao.obtenerLibroPorId(cesta.getIdLibro());
        Image image = new Image(libro.getRutaImagen());
        imageView.setImage(image);
        tituloLabel.setText(libro.getTitulo());	
        setLibro(libro);
    }
    public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
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

}
