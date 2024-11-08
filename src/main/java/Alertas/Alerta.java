package Alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	// Método para mostrar una alerta
	private static void mostrarAlerta(String title,String content,AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(content);
        alerta.showAndWait();
    }
	
	//Metodo para mostrar un error
	public static void mostrarError(String title,String content) {
		mostrarAlerta(title, content, AlertType.ERROR);
	}
	
	//Metodo para mostrar un error
	public static void mostrarInformacion(String title,String content) {
		mostrarAlerta(title, content, AlertType.INFORMATION);
	}
	
	//Metodo para mostrar un error
	public static void mostrarConfirmacion(String title,String content) {
		mostrarAlerta(title, content, AlertType.CONFIRMATION);
	}
}
