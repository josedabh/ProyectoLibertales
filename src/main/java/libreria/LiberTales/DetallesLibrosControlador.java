package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.DetallesLibroDAO;
import dto.Libro;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DetallesLibrosControlador {
    
    @FXML
    private ImageView imagenDetalle;
    @FXML
    private Label etiquetaTitulo;
    @FXML
    private Text textoSinopsis;
    @FXML
    private Label etiquetaPrecio;
    @FXML
    private Button botonUsuario;
    @FXML
    private Button botonCarrito;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button botonVolver;
    @FXML
    private Libro libro;
    
    // Instancia del DAO para manejar las operaciones en la base de datos
    private DetallesLibroDAO detallesLibroDAO;  
    
    public DetallesLibrosControlador() {
        // Inicializar la instancia del DAO
        detallesLibroDAO = new DetallesLibroDAO();
    }
    
    @FXML
   	private void cambiarALogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector() == null &&
       			SesionAdmin.getInstancia().getIdAdmin() == null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Iniciar sesión");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
       	} else if(SesionAdmin.getInstancia().getIdAdmin() != null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Administración");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("modificarusuario.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Modificar usuario");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		}
   	}
	
    @FXML
   	private void cambiarACarrito() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonCarrito.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Carrito");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir al carrito", "Primero, tienes que iniciar sesión");
   		}
   	}
	
    @FXML
   	private void cambiarAFavoritos() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonFavoritos.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Favoritos");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
   		}
   	}
    
    // Este método se invoca para establecer los detalles del libro
    public void setDetalles(Libro libro) {
        Image imagen = new Image(libro.getRutaImagen());
        imagenDetalle.setImage(imagen);
        etiquetaTitulo.setText(libro.getTitulo());
        textoSinopsis.setText(libro.getSinopsis());
        etiquetaPrecio.setText(String.format("€ %.2f", libro.getPrecioCompra())); 
        setLibro(libro);
    }
    
    public void inicializar() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        System.out.println("Funciona: " + idLector);
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
	}

    @FXML
    private void agregarAlCarrito() throws IOException {
        Libro libro = getLibro(); 
        detallesLibroDAO.agregarACesta(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());
		Alerta.mostrarInformacion("Añadido al carrito", "El libro se ha añadido al carrito");
    }
    
    @FXML
    private void agregarAFavoritos() throws IOException {
        Libro libro = getLibro(); 
        detallesLibroDAO.agregarAFavoritos(SesionUsuario.getInstancia().getIdLector(), libro.getId_libro());
		Alerta.mostrarInformacion("Añadido a favoritos", "El libro se ha añadido a la lista de favoritos");
    }

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	@FXML
    private void cambiarAPaginaPrincipal() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) botonVolver.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Página principal");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}
