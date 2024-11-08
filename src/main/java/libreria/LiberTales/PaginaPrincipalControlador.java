package libreria.LiberTales;

import java.io.IOException;

import javafx.fxml.FXML;

public class PaginaPrincipalControlador {
	
	@FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}

}
