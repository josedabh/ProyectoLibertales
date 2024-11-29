package libreria.LiberTales;

import java.io.IOException;

import Alertas.Alerta;
import dao.OlvidarDAO;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OlvidarControlador {
	
	// Botones y campos de la interfaz grafica 'olvidar.fxml'
	@FXML
    private Button botonUsuario;
	
    @FXML
    private Button botonCesta;
    
    @FXML
    private Button botonFavorito;
    
    @FXML
    private Button botonAtras;
	
    @FXML
    private TextField correo;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private Button botonEnviar;
    
    @FXML
    private Label mensaje;
    
	@FXML 
	private Button alquilerboton;
    
    @FXML
    public void initialize() {
        // Listener para validar el campo de correo en tiempo real
        correo.textProperty().addListener((observable, oldValue, newValue) -> {
        	// Si el correo no es valido
            if (!esCorreoValido(newValue)) { 
            	// Establece un borde rojo 
                correo.setStyle("-fx-border-color: red;");
                // Muestra un mensaje al usuario de que el correo no es valido
                mensaje.setText("Por favor, introduce un correo válido"); 
            } else {
                correo.setStyle(null); // Limpia estilos de error
                mensaje.setText(""); // Limpia el mensaje
            }
        });

        // Listener para validar el campo de nombre en tiempo real
        nombre.textProperty().addListener((observable, oldValue, newValue) -> {
        	// Si el nombre esta vacio
            if (newValue.trim().isEmpty()) {
            	// Establece un borde rojo
                nombre.setStyle("-fx-border-color: red;");
                // Muestra un mensaje al usuario de que el nombre no puede estar vacio
                mensaje.setText("El nombre no puede estar vacío");
            } else {
                nombre.setStyle(null); // Limpia estilos de error
                mensaje.setText(""); // Limpia el mensaje
            }
        });
    }

    
    // Metodo para cambiar a la ventana de iniciar sesion
    @FXML
   	private void ventanaIniciarSesion() throws IOException {
    	// Verificamos si hay un usuario activo
       	if(SesionUsuario.getInstancia().getIdLector()==null &&
       			SesionAdmin.getInstancia().getIdAdmin()==null) {
       		try {
       			// Carga la ventana de iniciar sesion
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
       		// Si hay una sesion de adminstrador, carga la ventana de administracion.fxml
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
   				// Si hay una sesion activa de usuario, carga la ventana de modificar usuario
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
    	// Si hay un usuario conectado
   		if(SesionUsuario.getInstancia().getIdLector()!=null) {
   			try {
   				// Carga la vista de cesta.fxml 
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
   			// Muestra una alerta al usuario de que debe de iniciar sesion 
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
	
    
    // Metodo para volver a la ventana anterior
	@FXML
    private void ventanaVoverAtras() throws IOException {
		try {
			// Carga la vista en este caso la de iniciar sesion porque la ventana anterior a Olvidar es iniciar sesion
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) botonAtras.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Iniciar sesion");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
	// Metodo para restablecer la contraseña
	@FXML
	public void restablecerContrasenia() {
		// Obtenemos los campo de texto y con el trim eliminamos los espacios en blanco
		String email = correo.getText().trim();
		String usuario = nombre.getText().trim();
		
		// Validaciones finales antes de enviar
		if (email.isEmpty() || usuario.isEmpty()) {
			mensaje.setText("Por favor, rellene todos los campos");
			return;
		}

		if (!esCorreoValido(email)) {
			mensaje.setText("Por favor, introduce un correo válido.");
			return;
		}
		
		try {
			// Llamamos al dao para realizar la operacion de recuperacion de contraseña
			OlvidarDAO dao = new OlvidarDAO();
			dao.recuperarContrasenia(email, usuario);
			
			// Muestra una alerta al usuario de que su nueva contraseña es la siguiente '123'
			Alerta.mostrarInformacion("Tu nueva contraseña es: ", "123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Metodo privado para validar si el formato del correo es valido
	private boolean esCorreoValido(String email) {
		// Expresión regular basica para validar correos electronicos
		return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
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
}

