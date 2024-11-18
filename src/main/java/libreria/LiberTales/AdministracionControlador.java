package libreria.LiberTales;

import java.io.IOException;

import dto.SesionAdmin;
import javafx.fxml.FXML;

public class AdministracionControlador {
	@FXML
	private void switchtoAdminLibros() throws IOException {
		App.setRoot("adminlibros");
	}
	
	@FXML
	private void switchtoAdminTransacciones() throws IOException {
		App.setRoot("admintransacciones");
	}
	
	@FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	@FXML
    private void cerrarSesion() throws IOException {
        // Limpiar el idLector de la sesión
        SesionAdmin.getInstancia().cerrarSesion();

        // Redirigir al usuario a la pantalla de inicio de sesión
        App.setRoot("paginaprincipal");
    }
}
