package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Conexion.ConexionBD;
import Conexion.CerrarConexion;

public class OlvidarDAO {

	// Metodo recuperar la contraseña
    public void recuperarContrasenia(String email, String nombre) {
    	// Variables usadas
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int encontrar = 0;
        int idUsuario = 0;
        try {
            con = ConexionBD.dameConexion();

            // Consulta para verificar si el usuario existe
            String sql = "SELECT id_lector FROM lector WHERE email = ? AND nombre = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, nombre);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("id_lector");
                System.out.println("Usuario encontrado con ID: " + idUsuario);
                encontrar++;
            } else {
                System.out.println("No se encontró un usuario con ese correo y nombre.");
            }

            // Si el usuario es encontrado, se actualiza su contraseña
            if (encontrar > 0) {
                actualizarContrasenia(idUsuario, "123"); // Cambia la contraseña aquí
                System.out.println("Contraseña actualizada correctamente.");
            }
            // Manejo de errores
        } catch (Exception e) {
            System.err.println("Error al recuperar la contraseña: " + e.getMessage());
        } finally {
            CerrarConexion.cerrar(con, pstmt, rs);
        }
    }

    // Metodo para actualizar la contraseña
    private void actualizarContrasenia(int idLector, String nuevaContra) {
    	// Variables usadas
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConexionBD.dameConexion();

            // Consulta para actualizar la contraseña
            String sql = "UPDATE lector SET contrasenia = ? WHERE id_lector = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevaContra);
            pstmt.setInt(2, idLector);

            int filasAfectadas = pstmt.executeUpdate();
            // Condicion si las filas afectadas es mayor a 1, se actualiza la contraseña a 123
            if (filasAfectadas > 0) {
                System.out.println("La contraseña fue actualizada para el usuario con email: " + idLector);
            } else {
                System.out.println("No se pudo actualizar la contraseña.");
            }
            // Manejo de errores
        } catch (Exception e) {
            System.err.println("Error al actualizar la contraseña: " + e.getMessage());
        } finally {
            CerrarConexion.cerrar(con, pstmt, null);
        }
    }
}
