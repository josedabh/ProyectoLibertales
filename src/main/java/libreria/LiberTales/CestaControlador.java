package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.CestaDAO;
import dto.Cesta;
import dto.SesionAdmin;
import dto.SesionUsuario;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;

public class CestaControlador {

    @FXML
    private VBox cestaVBox; 

    @FXML
    private ScrollPane scrollPaneCesta;
    
    @FXML
    private TilePane tilePaneCesta;
	@FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavorito;
    @FXML
    private Button botonAtras;
    @FXML
    private HBox contenedorCesta;
    
    private CestaDAO cestaDAO;
    
    // Constructor simple
    public CestaControlador() {
        cestaDAO = new CestaDAO(); // Inicializamos el objeto DAO
    }

    @FXML
    public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
            
            // Aquí puedes usar idLector para cargar datos específicos
        } else {
            System.out.println("No hay ID de lector en la sesión.");
        }
        cargarCesta();
    }
    // Metodo para cargar cesta
    private void cargarCesta() {
		try {
			//Lista que recoge los libros de cesta y con SesionUsuario.getInstancia().getIdLector() recoge el idLector
			List<Cesta> librosEnCesta = cestaDAO.obtenerCesta(SesionUsuario.getInstancia().getIdLector());
            
            for(Cesta itemCesta : librosEnCesta) {
            	//Se lo mandas las lista a cardscesta
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardscesta.fxml"));
                //Cargas el Hbox o vbox que hayas colocado en cardscesta
                HBox carta = loader.load();

                // Obtener el controlador de la carta y pasar los datos
                CardsCesta controladorCesta = loader.getController();
                //Le pasas la informacion de la cesta en setDatos que lo coje de CardsCesta
                controladorCesta.establecerDatos(itemCesta);

                // Agregar la carta al contenedor
                tilePaneCesta.getChildren().add(carta);
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

