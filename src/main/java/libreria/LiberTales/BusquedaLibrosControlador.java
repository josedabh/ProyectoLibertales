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
    private ScrollPane scrollPaneBusqueda;
    
    @FXML
    private TilePane panelLibrosBusqueda;
    
    @FXML
    private TextField campoBusqueda; // Campo de texto para ingresar la búsqueda
    
    @FXML
    private Button botonBuscar;
    
    @FXML
    private Button botonVolver;
    
    @FXML
    private Button botonUsuario;
    @FXML
    private Button botonCesta;
    @FXML
    private Button botonFavoritos;
    @FXML
    private Button alquilerboton;
    
    private Busqueda busqueda;

    public void inicializar() {
        // Configura el campo de búsqueda inicial
        if (busqueda != null) {
            this.campoBusqueda.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }

        // Agrega un listener al campo de búsqueda para actualizar al escribir
        campoBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            busqueda.setBuscar(newValue);
            cargarLibros(newValue);
        });
    }

    private void cargarLibros(String textoBusqueda) {
        panelLibrosBusqueda.getChildren().clear();

        LibroDAO libroDAO = new LibroDAO();
        List<Libro> listaLibros = (textoBusqueda == null || textoBusqueda.isEmpty())
            ? libroDAO.obtenerTodosLosLibros()
            : libroDAO.buscarLibrosPorTitulo(textoBusqueda);

        for (Libro libro : listaLibros) {
            try {
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("cardslibros.fxml"));
                VBox cartaLibro = cargador.load();

                CardsLibros controladorCarta = cargador.getController();
                controladorCarta.setDatos(libro);

                panelLibrosBusqueda.getChildren().add(cartaLibro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setBusqueda(Busqueda busqueda) {
        this.busqueda = busqueda;
        if (campoBusqueda != null) {
            campoBusqueda.setText(busqueda.getBuscar());
            cargarLibros(busqueda.getBuscar());
        }
    }
    
    @FXML
   	private void cambiarALogin() throws IOException {
       	if(SesionUsuario.getInstancia().getIdLector() == null &&
       			SesionAdmin.getInstancia().getIdAdmin() == null) {
       		try {
   		        FXMLLoader cargador = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
   		        Parent root = cargador.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Iniciar sesión");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
       	} else if(SesionAdmin.getInstancia().getIdAdmin() != null) {
       		try {
   		        FXMLLoader cargador = new FXMLLoader(getClass().getResource("administracion.fxml"));
   		        Parent root = cargador.load();
   		        Stage stage = (Stage) botonUsuario.getScene().getWindow();
   		        stage.setScene(new Scene(root));
   		        stage.setTitle("Administración");
   		        stage.show();
   		    } catch (IOException e) {
   		        e.printStackTrace();
   		    }
   		} else {
   			try {
   		        FXMLLoader cargador = new FXMLLoader(getClass().getResource("modificarusuario.fxml"));
   		        Parent root = cargador.load();
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
   		if(SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader cargador = new FXMLLoader(getClass().getResource("cesta.fxml"));
   		        Parent root = cargador.load();
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
   	private void cambiarAFavoritos() throws IOException {
   		if(SesionUsuario.getInstancia().getIdLector() != null) {
   			try {
   		        FXMLLoader cargador = new FXMLLoader(getClass().getResource("favorito.fxml"));
   		        Parent root = cargador.load();
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
	
	@FXML
    private void cambiarAPaginaPrincipal() throws IOException {
		try {
	        FXMLLoader cargador = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	        Parent root = cargador.load();
	        Stage stage = (Stage) botonVolver.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Página principal");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
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
}
