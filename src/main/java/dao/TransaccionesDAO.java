package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Conexion.CerrarConexion;
import Conexion.ConexionBD;
import dto.Transacciones;

public class TransaccionesDAO {

    // Método para insertar una nueva transacción
    public void insertarTransaccion(Transacciones transaccion, Transacciones.tipo_transaccion tipo) {
        String query = "INSERT INTO transacciones (id_transaccion, id_libro, id_lector, tipo, fecha_inicio, fecha_fin, precio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try  {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, transaccion.getId_transaccion());
            stmt.setInt(2, transaccion.getId_libro());
            stmt.setInt(3, transaccion.getId_lector());
            stmt.setString(4, tipo.name()); // Guardamos el tipo de transacción como cadena
            stmt.setDate(5, new java.sql.Date(transaccion.getFecha_inicio().getTime()));
            stmt.setDate(6, new java.sql.Date(transaccion.getFecha_fin().getTime()));
            stmt.setDouble(7, transaccion.getPrecio());
            System.out.println("Transacción añadida");
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una transacción por su ID
    public Transacciones obtenerTransaccionPorId(int id_transaccion) {
        String query = "SELECT * FROM transacciones WHERE id_transaccion = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_transaccion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Transacciones(
                        rs.getInt("id_transaccion"),
                        rs.getInt("id_libro"),
                        rs.getInt("id_lector"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getDouble("precio")
                );
            }
            CerrarConexion.cerrar(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todas las transacciones
    public List<Transacciones> obtenerTodasLasTransacciones() {
        List<Transacciones> transacciones = new ArrayList<>();
        String query = "SELECT * FROM transacciones";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
        	ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transacciones transaccion = new Transacciones(
                        rs.getInt("id_transaccion"),
                        rs.getInt("id_libro"),
                        rs.getInt("id_lector"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getDouble("precio")
                );
                transacciones.add(transaccion);
            }
            CerrarConexion.cerrar(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    // Método para actualizar una transacción existente
    public void actualizarTransaccion(Transacciones transaccion, Transacciones.tipo_transaccion tipo) {
        String query = "UPDATE transacciones SET id_libro = ?, id_lector = ?, tipo = ?, fecha_inicio = ?, fecha_fin = ?, precio = ? WHERE id_transaccion = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, transaccion.getId_libro());
            stmt.setInt(2, transaccion.getId_lector());
            stmt.setString(3, tipo.name());
            stmt.setDate(4, new java.sql.Date(transaccion.getFecha_inicio().getTime()));
            stmt.setDate(5, new java.sql.Date(transaccion.getFecha_fin().getTime()));
            stmt.setDouble(6, transaccion.getPrecio());
            stmt.setInt(7, transaccion.getId_transaccion());
            System.out.println("Transacción actualizada");
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una transacción por su ID
    public void eliminarTransaccion(int id_transaccion) {
        String query = "DELETE FROM transacciones WHERE id_transaccion = ?";
        try {
        	Connection conn = ConexionBD.dameConexion();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_transaccion);
            CerrarConexion.cerrar(conn, stmt, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
