package libreria.LiberTales;

import java.io.IOException;

import javafx.fxml.FXML;

public class AdminTransaccionesController {

	@FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	@FXML
	private void switchtoAdministracion() throws IOException{
		App.setRoot("administracion");
	}
}
