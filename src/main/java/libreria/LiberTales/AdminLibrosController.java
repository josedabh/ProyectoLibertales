package libreria.LiberTales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Alertas.Alerta;
import Conexion.ConexionBD;
import dto.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLibrosController {

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
	private TextField campoSinopsis;
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
	private void addBookToDatabase() {
		// Obtener valores del formulario
		String titulo = campoTitulo.getText();
		String autor = campoAutor.getText();
		String editorial = campoEditorial.getText();
		int anioPublicacion = Integer.parseInt(campoAnio_publicacion.getText());
		double precioCompra = Double.parseDouble(campoPrecioCompra.getText());
		double precioAlquiler = Double.parseDouble(campoPrecioAlquiler.getText());
		String sinopsis = campoSinopsis.getText();

		String titulosql = "SELECT COUNT(*) FROM Libro WHERE titulo = ?";
		String sql = "INSERT INTO Libro (titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConexionBD.dameConexion();
				PreparedStatement Stmt1 = conn.prepareStatement(titulosql);
				PreparedStatement Stmt2 = conn.prepareStatement(sql)) {
			// Verificar si el titulo del libro ya existe en la base de datos
			Stmt1.setString(1, titulo);
			ResultSet resultSet = Stmt1.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);

			if (count > 0) {
				// Si el ID ya existe, mostrar un mensaje de error
				System.out.println("Ese libro ya existe");
				return;
			}

			// Si el titulo no existe, hacer inserción
			Stmt2.setString(1, titulo);
			Stmt2.setString(2, autor);
			Stmt2.setString(3, editorial);
			Stmt2.setInt(4, anioPublicacion);
			Stmt2.setDouble(5, precioCompra);
			Stmt2.setDouble(6, precioAlquiler);
			Stmt2.setString(7, sinopsis);

			int rowsInserted = Stmt2.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Libro añadido correctamente");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void switchtoLogin() throws IOException {
    	if(SesionUsuario.getInstancia().getIdLector()==null) {
    		try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("iniciarsesion.fxml"));
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
	private void switchtoAdministracion() throws IOException {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("administracion.fxml"));
	        Parent root = loader.load();
	        Stage stage = (Stage) backButton.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
