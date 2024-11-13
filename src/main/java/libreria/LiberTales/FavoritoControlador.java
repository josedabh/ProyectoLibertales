package libreria.LiberTales;

import dao.LibroDAO;
import dao.CestaDAO;
import dto.Cesta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class FavoritoControlador {

    @FXML
    private VBox favoritosVBox;  // VBox en FXML donde se mostrarán los favoritos
    @FXML
    private Label favoritosLabel;  // Etiqueta para mostrar "Favoritos"

    private CestaDAO cestaDAO;
    private LibroDAO libroDAO;  // Asegúrate de tener este DAO para consultar los detalles de los libros
    private int idLector;  // ID del lector, puede ser obtenido de sesión o login

    public FavoritoControlador() {
        // Inicialización de los DAOs
        cestaDAO = new CestaDAO();
        libroDAO = new LibroDAO();  // Suponiendo que tienes este DAO para obtener libros
    }

    @FXML
    public void initialize() {
        // Configuración inicial del controlador
        // Por ejemplo, obtener el ID del lector desde sesión o de un login
        idLector = 1;  // Asignar un ID de lector de prueba o obtenerlo desde sesión

        // Cargar los libros favoritos (en la cesta del lector)
        cargarFavoritos();
    }

    // Método para cargar los libros en la cesta del lector (favoritos)
    private void cargarFavoritos() {
        // Obtener los libros de la cesta del lector
        List<Cesta> cesta = cestaDAO.obtenerCesta(idLector);

        // Limpiar el VBox antes de agregar nuevos elementos
        favoritosVBox.getChildren().clear();

        // Recorrer los libros de la cesta y agregarlos al VBox
        for (Cesta item : cesta) {
            // Obtener el idLibro para obtener el título y la imagen del libro
            int idLibro = item.getIdLibro();
            String titulo = obtenerTituloPorId(idLibro);
            String imagenUrl = obtenerImagenUrlPorId(idLibro);

            // Crear un HBox para cada libro en la cesta
            HBox libroHBox = new HBox(10);  // Espacio entre la imagen y el título
            libroHBox.setStyle("-fx-padding: 10;");

            // Crear la imagen del libro
            ImageView imageView = new ImageView();
            imageView.setFitHeight(141);
            imageView.setFitWidth(121);

            try {
                // Intentar cargar la imagen
                imageView.setImage(new Image(imagenUrl));
            } catch (Exception e) {
                // Si ocurre un error, se carga una imagen por defecto
                imageView.setImage(new Image("@../../libros/islan.png"));
            }

            // Crear el título del libro
            Label tituloLabel = new Label(titulo);
            tituloLabel.setStyle("-fx-font-size: 16px;");

            // Añadir la imagen y el título al HBox
            libroHBox.getChildren().addAll(imageView, tituloLabel);

            // Agregar el HBox al VBox
            favoritosVBox.getChildren().add(libroHBox);
        }
    }

    // Método para obtener el título del libro por su id
    private String obtenerTituloPorId(int idLibro) {
        // Consultar la base de datos para obtener el título del libro
        return libroDAO.obtenerTituloPorId(idLibro);  // Reemplaza con tu lógica para consultar la base de datos
    }

    // Método para obtener la URL de la imagen del libro por su id
    private String obtenerImagenUrlPorId(int idLibro) {
        // Consultar la base de datos para obtener la URL de la imagen del libro
        return libroDAO.obtenerImagenUrlPorId(idLibro);  // Reemplaza con tu lógica para consultar la base de datos
    }
}

