package libreria.LiberTales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar la vista principal
        scene = new Scene(loadFXML("paginaprincipal"));
        stage.setScene(scene);
        stage.setTitle("Página Principal");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        // Cambiar a una nueva vista sin datos adicionales
        scene.setRoot(loadFXML(fxml));
    }

    // Cargar el archivo FXML
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}
