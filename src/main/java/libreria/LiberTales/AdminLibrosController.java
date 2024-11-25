package libreria.LiberTales;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dto.SesionAdmin;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminLibrosController {

    @FXML
    private ImageView vistaLibro;
    @FXML
    private TextField campoidLibro;
    @FXML
    private TextField campoTitulo;
    @FXML
    private TextField campoAutor;
    @FXML
    private TextField campoEditorial;
    @FXML
    private TextField campoAnio_publicacion;
    @FXML
    private TextField campoPrecioCompra;
    @FXML
    private TextField campoPrecioAlquiler;
    @FXML
    private TextArea campoSinopsis;
    @FXML
    private TextField campoRutaImagen;
    @FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button backButton;
    @FXML
    private Button botonImagen;
    @FXML
    private Button botonAnadirLibro;

    private File selectFile;


    @FXML
    private void openFilechoose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif")
        );

        selectFile = fileChooser.showOpenDialog(null);

        if (selectFile != null) {
            try {
                // Mostrar la imagen seleccionada en el ImageView
                Image image = new Image(selectFile.toURI().toString());
                vistaLibro.setImage(image);

            } catch (Exception e) {
                Alerta.mostrarError("Error al cargar la imagen", "No se pudo cargar la imagen seleccionada.");
                e.printStackTrace();
            }
        } else {
            Alerta.mostrarError("Error de selección", "No se ha seleccionado ninguna imagen.");
        }
    }

    @FXML
    private void addBookToDatabase() {
        // Obtener valores del formulario
        String titulo = campoTitulo.getText();
        String autor = campoAutor.getText();
        String editorial = campoEditorial.getText();
        int anioPublicacion = Integer.parseInt(campoAnio_publicacion.getText());
        double precioCompra = Double.parseDouble(campoPrecioCompra.getText());
        double precioAlquiler = Double.parseDouble(campoPrecioAlquiler.getText());
        String sinopsis = campoSinopsis.getText();
        boolean anadido = false;

        // Ruta final de la imagen dentro del proyecto Maven
        Path destino = null;

        if (selectFile != null) {
            destino = Path.of("src/main/resources/libros", selectFile.getName());
            try {
                Files.createDirectories(destino.getParent()); // Crear carpeta si no existe
                Files.copy(selectFile.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                Alerta.mostrarError("Error al mover la imagen", "No se pudo mover la imagen al directorio destino.");
                e.printStackTrace();
                return; // No proceder con la inserción si falla el movimiento de la imagen
            }
        }

        // Ruta relativa para guardar en la base de datos
        String rutaImagen = (destino != null) ? "@../../libros/" + selectFile.getName() : null;

        //Quitar titulossql
        String titulosql = "SELECT COUNT(*) FROM Libro WHERE titulo = ?";
        String sql = "INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.dameConexion();
             PreparedStatement Stmt1 = conn.prepareStatement(titulosql);
             PreparedStatement Stmt2 = conn.prepareStatement(sql)) {

            // Verificar si el título ya existe
            Stmt1.setString(1, titulo);
            ResultSet resultSet = Stmt1.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                System.out.println("Ese libro ya existe");
                return;
            }

            // Insertar el nuevo libro
            Stmt2.setString(1, titulo);
            Stmt2.setString(2, autor);
            Stmt2.setString(3, editorial);
            Stmt2.setInt(4, anioPublicacion);
            Stmt2.setDouble(5, precioCompra);
            Stmt2.setDouble(6, precioAlquiler);
            Stmt2.setString(7, sinopsis);
            Stmt2.setString(8, rutaImagen); // Guardar la ruta relativa de la imagen

            int rowsInserted = Stmt2.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Libro añadido correctamente");
                anadido = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(anadido) {
        	try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
    	        Parent root = loader.load();
    	        Stage stage = (Stage) botonAnadirLibro.getScene().getWindow();
    	        stage.setScene(new Scene(root));
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
        } else {
        	Alerta.mostrarError("Error", "Introduce los datos primero");
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
	private void switchtoAdministracion() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.setTitle("Administración");
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
