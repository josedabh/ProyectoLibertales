package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;
import dao.LibroDAO;
import dto.Busqueda;
import dto.Lector;
import dto.Libro;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaginaPrincipalControlador {
	
	@FXML
    private ScrollPane panelDesplazable;
    
    @FXML
    private TilePane tilePaneCartas;
    
    @FXML
    private TextField campoBusqueda; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button botonBuscar; 
    
    @FXML
    private Button botonUsuario;
    
    @FXML
    private Button botonCesta;
    
    @FXML
    private Button botonMensajes;
    
	@FXML
	private VBox contenedorCartas;
	
	 @FXML
	 private Button alquilerboton; 
	 
	public Lector lector;
	
	@FXML
	public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        Integer idAdmin = SesionAdmin.getInstancia().getIdAdmin();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
            
            // Aquí puedes usar idLector para cargar datos específicos
        } else if (idAdmin != null) {
        	System.out.println("ID del administrador en la sesión: " + idAdmin);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
        cargarCartas();
    }
	
	@FXML
   	private void cambiarALogin() throws IOException {
       	if (SesionUsuario.getInstancia().getIdLector() == null &&
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
       	} else if (SesionAdmin.getInstancia().getIdAdmin() != null) {
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
   	private void cambiarACesta() throws IOException {
   		if (SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonCesta.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Cesta");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			Alerta.mostrarError("Error al ir a la cesta", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
    
    
	
    @FXML
   	private void cambiarAFavorito() throws IOException {
   		if (SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonMensajes.getScene().getWindow();
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
	// Metodo para volver a la pagina de libros alquilados
	   @FXML
	   	private void ventanaAlquiler() throws IOException {
	   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
	   			try {
	   				// Carga la vista de la pagina principal 'alquiler.fxml'
	   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("alquiler.fxml"));
	   		        Parent root = loader.load();
	   		        Stage stage = (Stage) alquilerboton.getScene().getWindow();
	   		        stage.setScene(new Scene(root));
	   		        stage.setTitle("Alquiler");
	   		        stage.show();
	   		    } catch (IOException e) {
	   		        e.printStackTrace();
	   		    }
	   		} else {
	   			Alerta.mostrarError("Error al ir a alquiler", "Primero, tienes que iniciar sesión");
	   		}
	   		
	   	}
	@FXML
	private void cambiarABusqueda() throws IOException {
	    // Crear una instancia de FXMLLoader y cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("busquedalibros.fxml"));
	    Parent root = loader.load();
	    
	    // Obtener el controlador de la vista cargada
	    BusquedaLibrosControlador controller = loader.getController();
	    
	    // Crear una instancia de Busqueda con el texto de búsqueda actual
	    Busqueda busqueda = new Busqueda(campoBusqueda.getText());
	    
	    // Pasar la instancia de Busqueda al controlador de la vista de búsqueda
	    controller.setBusqueda(busqueda);
	    
	    // Mostrar la nueva escena
	    Stage stage = (Stage) botonBuscar.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Búsqueda de libros");
	    stage.show();
	}
	
	private void cargarCartas() {
		try {
			LibroDAO libroDAO = new LibroDAO();
            List<Libro> listaLibros = libroDAO.obtenerTodosLosLibros();
            
            for (Libro libro : listaLibros) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();

                // Obtener el controlador de la carta y pasar los datos
                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro);

                // Agregar la carta al contenedor
                tilePaneCartas.getChildren().add(carta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
