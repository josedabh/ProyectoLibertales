package dao;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Cesta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CestaDAO {

    // Método para eliminar un libro de la cesta de un lector
    public void eliminarDeLaCesta(int idLector, int idLibro) {
        Connection conexion = null;
        PreparedStatement pst = null;
        try {
            // Establecer la conexión a la base de datos
            conexion = ConexionBD.dameConexion();

            // SQL para eliminar un libro de la cesta del usuario
            String sql = "DELETE FROM cesta WHERE id_lector = ? AND id_libro = ?";
            pst = conexion.prepareStatement(sql);

            // Establecer los parámetros de la consulta
            pst.setInt(1, idLector);
            pst.setInt(2, idLibro);

            // Ejecutar la consulta
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro de la cesta: " + e.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }


    // Método para obtener todos los libros en la cesta de un lector
    public List<Cesta> obtenerCesta(int idLector) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cesta> cesta = new ArrayList<>();

        try {
            con = ConexionBD.dameConexion();
            String query = "SELECT id_libro FROM Cesta WHERE id_lector = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLector);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("id_libro");
                cesta.add(new Cesta(idLector, idLibro));
            }
            
            CerrarConexion.cerrar(con, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cesta;
    }
    
 // Método para realizar la compra e insertar en la tabla Compra
    public void comprarLibro(int idLector, int idLibro, Date fechaCompra) throws SQLException {
        Connection con = null;
        PreparedStatement sentenciaCompra = null;
        PreparedStatement sentenciaEliminarDeCesta = null;

        String sqlCompra = "INSERT INTO Compra (id_lector, id_libro, fecha_compra) VALUES (?, ?, ?)";
        String sqlEliminarDeCesta = "DELETE FROM Cesta WHERE id_lector = ? AND id_libro = ?";
        try {
            // Obtener la conexión
            con = ConexionBD.dameConexion();

            // Iniciar la transacción
            con.setAutoCommit(false); // Desactivamos el autocommit para manejarlo manualmente

            // Preparar la consulta para insertar la compra
            sentenciaCompra = con.prepareStatement(sqlCompra);
            sentenciaCompra.setInt(1, idLector);
            sentenciaCompra.setInt(2, idLibro);
            sentenciaCompra.setDate(3, fechaCompra);

            // Ejecutar la inserción
            int filasInsertadas = sentenciaCompra.executeUpdate();

            // Si no se insertaron filas en la tabla Compra, lanzamos una excepción
            if (filasInsertadas == 0) {
                throw new SQLException("No se pudo realizar la compra.");
            }

            // Preparar la consulta para eliminar el libro de la cesta
            sentenciaEliminarDeCesta = con.prepareStatement(sqlEliminarDeCesta);
            sentenciaEliminarDeCesta.setInt(1, idLector);
            sentenciaEliminarDeCesta.setInt(2, idLibro);

            // Ejecutar la eliminación del libro de la tabla Cesta
            int filasEliminadas = sentenciaEliminarDeCesta.executeUpdate();

            // Si no se eliminó ninguna fila, lanzamos una excepción
            if (filasEliminadas == 0) {
                throw new SQLException("No se pudo eliminar el libro de la cesta.");
            }

            // Si ambas operaciones fueron exitosas, confirmar la transacción
            con.commit();

        } catch (SQLException e) {
            // Si ocurre un error, hacer rollback de la transacción
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.err.println("Error al realizar la compra o eliminar el libro de la cesta: " + e.getMessage());
            throw e;  // Lanzamos la excepción para que se maneje en otro lugar

        } finally {
            // Cerrar los recursos
            CerrarConexion.cerrar(con, sentenciaCompra, null);
            CerrarConexion.cerrar(con, sentenciaEliminarDeCesta, null);
        }
    }
}

