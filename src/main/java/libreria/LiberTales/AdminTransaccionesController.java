package libreria.LiberTales;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminTransaccionesController {

	@FXML
	private void switchtoLogin() throws IOException{
		App.setRoot("iniciarsesion");
	}
	
	@FXML
	private void switchtoAdministracion() throws IOException{
		App.setRoot("administracion");
	}
	
	@FXML
	private void showTransactions() {
		TableView transacciones;
	    TableColumn fechaColumn;
	    TableColumn metodoColumn;
	    TableColumn emailColumn;
	    TableColumn tituloColumn;
	    TableColumn precioColumn;
	    ObservableList listaTransacciones;

	    listaTransacciones = FXCollections.observableArrayList();
	    
	    
	}
}
