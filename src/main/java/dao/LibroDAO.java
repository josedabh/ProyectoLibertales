package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Libro;

public class LibroDAO {

    private Connection connection;

    public LibroDAO() {
        this.connection = connection;
    }

    // Método para insertar un nuevo libro en la base de datos
    public void insertarLibro(Libro libro) {
        String query = "INSERT INTO libros (id_libro, titulo, autor, editorial, anio_publicacion, precioCompra, precioAlquiler, sinopsis, rutaImagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, libro.getId_libro());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnio_publicaion());
            stmt.setDouble(6, libro.getPrecioCompra());
            stmt.setDouble(7, libro.getPrecioAlquiler());
            stmt.setString(8, libro.getSinopsis());
            stmt.setString(9, libro.getRutaImagen());
            System.out.println("Libro añadido");
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un libro por su ID
    public Libro obtenerLibroPorId(int id_libro) {
        String query = "SELECT * FROM libros WHERE id_libro = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_libro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_publicacion"),
                        rs.getDouble("precioCompra"),
                        rs.getDouble("precioAlquiler"),
                        rs.getString("sinopsis"),
                        rs.getString("rutaImagen")
                );
            }
            CerrarConexion.cerrar(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM libros";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
        	ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                libros.add(new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editorial"),
                        rs.getInt("anio_publicacion"),
                        rs.getDouble("precioCompra"),
                        rs.getDouble("precioAlquiler"),
                        rs.getString("sinopsis"),
                        rs.getString("rutaImagen")
                ));
            }
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    // Método para actualizar un libro existente
    public void actualizarLibro(Libro libro) {
        String query = "UPDATE libros SET titulo = ?, autor = ?, editorial = ?, anio_publicacion = ?, precioCompra = ?, precioAlquiler = ?, sinopsis = ?, rutaImagen = ? WHERE id_libro = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getEditorial());
            stmt.setInt(4, libro.getAnio_publicaion());
            stmt.setDouble(5, libro.getPrecioCompra());
            stmt.setDouble(6, libro.getPrecioAlquiler());
            stmt.setString(7, libro.getSinopsis());
            stmt.setString(8, libro.getRutaImagen());
            stmt.setInt(9, libro.getId_libro());
            System.out.println("Libro actualizado");
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un libro por su ID
    public void eliminarLibro(int id_libro) {
        String query = "DELETE FROM libros WHERE id_libro = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_libro);
            System.out.println("Libro eliminado");
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para obtener el título de un libro por su id
    public String obtenerTituloPorId(int idLibro) {
        String titulo = null;

        try (Connection conn = ConexionBD.dameConexion()) {
            String query = "SELECT titulo FROM libros WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idLibro);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    titulo = rs.getString("titulo");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titulo != null ? titulo : "Título no disponible";
    }

    // Método para obtener la URL de la imagen del libro por su id
    public String obtenerImagenUrlPorId(int idLibro) {
        String imagenUrl = null;
        try (Connection conn = ConexionBD.dameConexion()) {
            String query = "SELECT imagen_url FROM libros WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idLibro);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    imagenUrl = rs.getString("imagen_url");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagenUrl != null ? imagenUrl : "@../../libros/default.png";
    
    
    
    }
}
