package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dao.LectorDAO;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaginaPrincipalControlador {
	
	@FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TilePane tilePaneCartas;
    
    @FXML
    private TextField searchField; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button searchButton; 
    
    @FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    
	@FXML
	private VBox contenedorCartas;
	
	 @FXML
	 private Button alquilerboton; 
	 
	public Lector lector;
	
	@FXML
   	private void switchtoLogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) userButton.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Iniciar sesión");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
       	} else if(SesionAdmin.getInstancia().getIdAdmin()!=null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) userButton.getScene().getWindow();
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
   		        Stage stage = (Stage) userButton.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Modificar usuario");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		}
   	}
	
    @FXML
   	private void switchToCesta() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("cesta.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) cartButton.getScene().getWindow();
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
   	private void switchToFavorito() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) messageButton.getScene().getWindow();
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
	private void switchToBusqueda() throws IOException {
	    // Crear una instancia de FXMLLoader y cargar el archivo FXML
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
	    Parent root = loader.load();
	    
	    // Obtener el controlador de la vista cargada
	    FavoritoControlador controller = loader.getController();
	    
	    // Mostrar la nueva escena
	    Stage stage = (Stage) cartButton.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Búsqueda libros");
	    stage.show();
	}
	
	private void cargarCartas() {
		try {
			LibroDAO libroDAO = new LibroDAO();
            List<Libro> listaLibros = libroDAO.obtenerTodosLosLibros();
            
            for(Libro libro: listaLibros) {
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

    public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        System.out.println("Funciona" + idLector);
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
        cargarLibros(null); // Carga todos los libros al iniciar la aplicación
    }
    private void cargarLibros(String searchText) {
        tilePaneCartas.getChildren().clear(); // Limpia el contenedor antes de agregar resultados

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros;
        
        if (searchText == null || searchText.isEmpty()) {
            listaLibros = libroDAO.obtenerTodosLosLibros(); // Obtiene todos los libros si no hay texto de búsqueda
        } else {
            listaLibros = libroDAO.buscarLibrosPorTitulo(searchText); // Obtiene libros filtrados por el título
        }
        
        for (Libro libro : listaLibros) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();
                
                // Obtener el controlador de la carta y establecer los datos del libro
                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro);

                tilePaneCartas.getChildren().add(carta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método que se ejecuta al hacer clic en el botón de búsqueda
    public void buscarLibros() {
        String searchText = searchField.getText(); // Obtiene el texto del campo de búsqueda
        cargarLibros(searchText); // Llama a cargarLibros con el texto de búsqueda
    }

}
