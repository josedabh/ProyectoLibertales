package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Conexion.CerrarConexion;
import Conexion.ConexionBD;

public class CompraDAO {
    
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

