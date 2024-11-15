package libreria.LiberTales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import dao.CestaDAO;
import dto.Cesta;
import dto.SesionUsuario;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;

public class CestaControlador {

    @FXML
    private VBox cestaVBox; // VBox donde se mostrarán los libros en la cesta

    @FXML
    private ScrollPane scrollPaneCesta;
    
    @FXML
    private TilePane tilePaneCesta;
    
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
        	Alerta.mostrarError("Error al cargar la cesta", "Se requiere iniciar sesión primero");
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
                controladorCesta.setDatos(itemCesta);

                // Agregar la carta al contenedor
                tilePaneCesta.getChildren().add(carta);
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
	    App.setRoot("cesta");
	}
	
	@FXML
	private void switchToFavorito() throws IOException {
	    App.setRoot("favorito");
	}
	
	@FXML
    private void switchToPagina() throws IOException {
        App.setRoot("paginaPrincipal");
    }
//    // Método para comprar un libro
//    private void comprarLibro(Cesta itemCesta) {
//        cestaDAO.agregarALaCesta(itemCesta.getIdLector(), itemCesta.getIdLibro());
//        System.out.println("Libro comprado: ID " + itemCesta.getIdLibro());
//        cargarLibrosEnCesta(); // Refresca la lista después de la acción
//    }
//
//    // Método para alquilar un libro
//    private void alquilarLibro(Cesta itemCesta) {
//        cestaDAO.alquilarLibro(itemCesta.getIdLector(), itemCesta.getIdLibro());
//        System.out.println("Libro alquilado: ID " + itemCesta.getIdLibro());
//        cargarLibrosEnCesta(); // Refresca la lista después de la acción
//    }
//
//    // Método para eliminar un libro de la cesta
//    private void eliminarLibroDeLaCesta(Cesta itemCesta) {
//        cestaDAO.eliminarDeLaCesta(itemCesta.getIdLector(), itemCesta.getIdLibro());
//        System.out.println("Libro eliminado de la cesta: ID " + itemCesta.getIdLibro());
//        cargarLibrosEnCesta(); // Refresca la lista después de la acción
//    }
	
}

