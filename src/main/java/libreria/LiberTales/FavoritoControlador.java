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
    private VBox favoritosVBox;  // VBox en FXML donde se mostrarán los favoritos
    @FXML
    private Label favoritosLabel;  // Etiqueta para mostrar "Favoritos"

    private FavoritoDAO favoritoDAO;
    private LibroDAO libroDAO;  // Asegúrate de tener este DAO para consultar los detalles de los libros
   
    public FavoritoControlador() {
        // Inicialización de los DAOs
        favoritoDAO = new FavoritoDAO();
        libroDAO = new LibroDAO();  // Suponiendo que tienes este DAO para obtener libros
    }

    @FXML
    public void initialize() {
        //Verificar si el usuario esta registrado
    	Integer idLector = SesionUsuario.getInstancia().getIdLector();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
            
        } else {
        	Alerta.mostrarError("Error al cargar la cesta", "Se requiere iniciar sesión primero");
            System.out.println("No hay ID de lector en la sesión.");
        } 
        // Cargar los libros favoritos (en la cesta del lector)
        cargarFavoritos();
    }
    //ERROR AL CARGAR LA IMAGEN
    // Método para cargar los libros en la cesta del lector (favoritos)
    private void cargarFavoritos() {
        // Obtener los libros de la cesta del lector
       try {
    	   List<Favorito> Listafavorito = favoritoDAO.obtenerFavoritos(SesionUsuario.getInstancia().getIdLector());

           for(Favorito itemFavorito : Listafavorito) {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("cardsfavorito.fxml"));
               HBox carta = loader.load();

               // Obtener el controlador de la carta y pasar los datos
               CardsFavorito controladorFavorito = loader.getController();
               controladorFavorito.setDatos(itemFavorito);

               // Agregar la carta al contenedor
               TilePaneFavorito.getChildren().add(carta);
           }
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
    
    @FXML
   	private void switchtoLogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
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
       	} else if(SesionAdmin.getInstancia().getIdAdmin()!=null) {
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
   	private void switchToCesta() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
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
   	private void switchToFavorito() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
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
   			Alerta.mostrarError("Error al ir a favoritos", "Primero, tienes que iniciar sesión");
   		}
   		
   	}
	
	@FXML
    private void switchToPagina() throws IOException {
		try {
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

