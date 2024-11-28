package libreria.LiberTales;

import dao.LibroDAO;
import dao.CestaDAO;
import dao.FavoritoDAO;
import dto.Cesta;
import dto.Favorito;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;

public class FavoritoControlador {
	
	// Botones y campos de la interfaz grafica 'favorito.fxml'
	@FXML
	private TilePane  TilePaneFavorito;
	
	@FXML
	private HBox contenedorFavorito;
	
	@FXML
    private Button botonUsuario;
	
    @FXML
    private Button botonCesta;
    
    @FXML
    private Button botonFavorito;
    
    @FXML
    private Button botonAtras;
    
    @FXML
    private VBox favoritosVBox; 

    @FXML
    private Label favoritosLabel;  

    @FXML
    private FavoritoDAO favoritoDAO;
    
    @FXML
    private LibroDAO libroDAO; 
   
    // Constructor para inicializar los DAO
    public FavoritoControlador() {
        favoritoDAO = new FavoritoDAO();
        libroDAO = new LibroDAO();  
    }

    // Metodo para inicializacion para cargar la vista
    @FXML
    public void initialize() {
        //Verificar si el usuario esta registrado
    	Integer idLector = SesionUsuario.getInstancia().getIdLector();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
            // Mostar una alerta al usuario si no ha iniciado sesion
        } else {
        	Alerta.mostrarError("Error al cargar la cesta", "Se requiere iniciar sesión primero");
            System.out.println("No hay ID de lector en la sesión.");
        } 
        // Cargar los libros favoritos 
        cargarFavoritos();
    }

    
    // Método para cargar los libros favoritos de un lector
    private void cargarFavoritos() {
        // Obtener los libros de la cesta del lector
       try {
    	   List<Favorito> Listafavorito = favoritoDAO.obtenerFavoritos(SesionUsuario.getInstancia().getIdLector());
    	   
           for(Favorito itemFavorito : Listafavorito) {
               // Carga la vista cardsfavorito.fxml
        	   FXMLLoader loader = new FXMLLoader(getClass().getResource("cardsfavorito.fxml"));
               HBox carta = loader.load();

               // Obtener el controlador de la carta y pasar los datos
               CardsFavorito controladorFavorito = loader.getController();
               controladorFavorito.estableceDatos(itemFavorito);

               // Agregar la carta al contenedor
               TilePaneFavorito.getChildren().add(carta);
           }
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
    
    // Metodo para llevarnos a la ventana de iniciar sesion
    @FXML
   	private void ventanaIniciarSesion() throws IOException {
    	// Verifica si hay un usuario activo en la sesion
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
       		// Si el usuario no ha iniciado sesion
       		try {
       			// Carga la vista de iniciar sesion y nos lleva alli
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Iniciar sesión");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
       	}
       	// Si el usuario ha iniciado como administrador
       	else if(SesionAdmin.getInstancia().getIdAdmin()!=null) {
       		try {
       			// Carga el fxml de administracion.fxml
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
   				// Si el usuario esta autenticado pero no es administrador, nos lleva y carga el fxml de modificarusuario.fxml
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
	
    // Metodo para cambiar a la ventana de cesta 'cesta.fxml'
    @FXML
   	private void ventanaCesta() throws IOException {
    	// Si hay un usuario en la sesion activa
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   				// Nos lleva a cesta.fxml
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
   			// Muestra una alerta al usuario de que para ir a cesta primero tiene que iniciar sesion
   			Alerta.mostrarError("Error al ir a la cesta", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
	
    // Metodo para cambiar a la ventana de favorito.fxml
    @FXML
   	private void ventanaFavorito() throws IOException {
	    // Si hay un usuario activo
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   				// Carga la vista favorito
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) botonFavorito.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Favoritos");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			// Si no hay un usuario activo pues muestra al usuario que tiene que iniciar sesion antes
   			Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
	
    // Metodo para volver a la pagina principal
	@FXML
    private void ventanaPaginaPrincipal() throws IOException {
		try {
			// Carga la vista de la pagina principal 'paginaprincipal.fxml'
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) botonAtras.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Página principal");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
}

