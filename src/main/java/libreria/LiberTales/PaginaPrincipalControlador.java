package libreria.LiberTales;

import java.io.IOException;
import javafx.fxml.FXML;

public class PaginaPrincipalControlador {

    @FXML
    private void switchtoLogin() throws IOException {
        App.setRoot("iniciarsesion");
    }

    @FXML
    private void switchToCesta() throws IOException {
        App.setRoot("cesta"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
    }
    
    @FXML
    private void switchToFavorito() throws IOException {
        App.setRoot("favorito"); // Cambia "cesta" por el nombre del archivo FXML de la cesta si es diferente
    }
}
