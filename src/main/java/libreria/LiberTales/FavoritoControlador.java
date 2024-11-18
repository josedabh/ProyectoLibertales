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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;

public class FavoritoControlador {
	@FXML
	private TilePane  TilePaneFavorito;
	@FXML
	private HBox contenedorFavorito;
    @FXML
    private VBox favoritosVBox;  // VBox en FXML donde se mostrarán los favoritos
    @FXML
    private Label favoritosLabel;  // Etiqueta para mostrar "Favoritos"

    private FavoritoDAO favoritoDAO;
    private LibroDAO libroDAO;  // Asegúrate de tener este DAO para consultar los detalles de los libros
    private int idLector;  // ID del lector, puede ser obtenido de sesión o login

    public FavoritoControlador() {
        // Inicialización de los DAOs
        favoritoDAO = new FavoritoDAO();
        libroDAO = new LibroDAO();  // Suponiendo que tienes este DAO para obtener libros
    }

    @FXML
    public void initialize() {
        //Verificar si el usuario esta registrado
    	Integer idLector = SesionUsuario.getInstancia().getIdLector();
    	Integer idAdmin = SesionAdmin.getInstancia().getIdAdmin();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);  
        }else if(idAdmin != null) {
        	System.out.println("ID del admin en la sesión: " + idAdmin); 
        }else {
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
   	private void switchtoLogin() throws IOException{
   		App.setRoot("iniciarsesion");
   	}
   	
   	@FXML
   	private void switchToCesta() throws IOException {
   	    App.setRoot("cesta"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
   	}
   	
   	@FXML
   	private void switchToFavorito() throws IOException {
   	    App.setRoot("favorito"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
   	}
   	
   	@FXML
       private void switchToPagina() throws IOException {
           App.setRoot("paginaPrincipal");
       }

    // Método para obtener el título del libro por su id
    private String obtenerTituloPorId(int idLibro) {
        // Consultar la base de datos para obtener el título del libro
        return libroDAO.obtenerTituloPorId(idLibro);  // Reemplaza con tu lógica para consultar la base de datos
    }

    // Método para obtener la URL de la imagen del libro por su id
    private String obtenerImagenUrlPorId(int idLibro) {
        // Consultar la base de datos para obtener la URL de la imagen del libro
        return libroDAO.obtenerImagenUrlPorId(idLibro);  // Reemplaza con tu lógica para consultar la base de datos
    }
}

