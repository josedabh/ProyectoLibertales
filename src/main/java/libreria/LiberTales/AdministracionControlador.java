package libreria.LiberTales;

import java.io.IOException;

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
}
