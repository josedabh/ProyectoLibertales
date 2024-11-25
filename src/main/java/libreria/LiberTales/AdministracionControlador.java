package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdministracionControlador {
	@FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button backButton;
    @FXML
    private Button botonLibros;
    @FXML
    private Button botonTransacciones;
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
	public void initialize() {
        Integer idLector = SesionUsuario.getInstancia().getIdLector();
        Integer idAdmin = SesionAdmin.getInstancia().getIdAdmin();
        if (idLector != null) {
            System.out.println("ID del lector en la sesión: " + idLector);
            
            // Aquí puedes usar idLector para cargar datos específicos
        } else if(idAdmin!=null) {
        	System.out.println("ID del admin en la sesión: " + idAdmin);
        }else {
            System.out.println("No hay ID de lector en la sesión.");
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
	
	@FXML
	private void switchtoAdminLibros() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminlibros.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) userButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Administración de libro");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void switchtoAdminTransacciones() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("admintransacciones.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) userButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@FXML
    private void cerrarSesion() throws IOException {
		// Redirigir al usuario a la pantalla de inicio de sesión
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paginaprincipal.fxml"));
	    Parent root = loader.load();
	    // Mostrar la nueva escena
	    Stage stage = (Stage) botonCerrarSesion.getScene().getWindow();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Página Principal");
	    stage.show();
	    
        // Limpiar el idAdmin de la sesión
        SesionAdmin.getInstancia().cerrarSesion();

    }
}
