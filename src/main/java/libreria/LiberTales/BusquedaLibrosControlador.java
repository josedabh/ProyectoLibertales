package libreria.LiberTales;

import java.io.IOException;
import java.util.List;

import Alertas.Alerta;
import dao.LibroDAO;
import dto.Busqueda;
import dto.Libro;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.application.Platform;
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

public class BusquedaLibrosControlador {

    @FXML
    private ScrollPane scrollBusqueda;
    
    @FXML
    private TilePane tilePaneBusqueda;
    
    @FXML
    private TextField searchField; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button searchButton; 
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    
    private Busqueda busqueda;

    public void initialize() {
        // Configura el campo de búsqueda inicial
        if (busqueda != null) {
            this.searchField.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }

        // Agrega un listener al campo de búsqueda para actualizar al escribir
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            busqueda.setBuscar(newValue);
            cargarLibros(newValue);
        });
    }

    private void cargarLibros(String searchText) {
        tilePaneBusqueda.getChildren().clear();

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros = (searchText == null || searchText.isEmpty())
            ? libroDAO.obtenerTodosLosLibros()
            : libroDAO.buscarLibrosPorTitulo(searchText);

        for (Libro libro : listaLibros) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox carta = loader.load();

                CardsLibros controladorCarta = loader.getController();
                controladorCarta.setDatos(libro);

                tilePaneBusqueda.getChildren().add(carta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
        if (searchField != null) {
            searchField.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }
    }
    
    @FXML
   	private void switchtoLogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
       		try {
   		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = loader.load();
   		        Stage stage = (Stage) userButton.getScene().getWindow();
   		        stage.setScene(new Scene(root));
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
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
}
