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

public class ModificarUsuarioControlador {
	
	@FXML
	private TextField campoNombre;
	@FXML
	private TextField campoDireccion;
	@FXML
	private TextField campoTelefono;
	
	private int idLector;
	@FXML
    private Button userButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button messageButton;
    @FXML
    private Button backButton;

	@FXML
	private void ingresarDatosbbdd() {
		this.idLector = idLector;

		String sql = "SELECT nombre, direccion, telefono FROM Lector WHERE id_lector = ?";

		try (Connection conn = ConexionBD.dameConexion();
			PreparedStatement stmt = conn.prepareStatement(sql)){

			stmt.setInt(1, idLector);
			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				campoNombre.setText(resultado.getString("nombre"));
				campoDireccion.setText(resultado.getString("direccion"));
				campoTelefono.setText(resultado.getString("telefono"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void guardarCambios() {
		String nombre = campoNombre.getText();
		String direccion = campoDireccion.getText();
		String telefono = campoTelefono.getText();

		String sql = "UPDATE Lector SET nombre = ?, direccion = ?, telefono = ? WHERE id_lector = ?";

		try (Connection conn = ConexionBD.dameConexion();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, nombre);
	            stmt.setString(2, direccion);
	            stmt.setString(3, telefono);
	            stmt.setInt(4, idLector);
	            
	            int filasActualizadas = stmt.executeUpdate();
	            
	            if (filasActualizadas > 0) {
	                System.out.println("Datos del lector actualizados correctamente.");
	            } else {
	                System.out.println("No se encontró el lector con el ID especificado.");
	            }
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void switchtoModificarUsuario() throws IOException {
		App.setRoot("modificarusuario");
	}
	
	private void switchtoIniciarSesion() throws IOException{
		App.setRoot("iniciarsesion");
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