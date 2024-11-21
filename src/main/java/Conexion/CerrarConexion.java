package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CerrarConexion {
																		
    //	Cierra la conexión, la sentencia y el resultado de la base de datos.
 
    public static void cerrar(Connection con, PreparedStatement sentencia, ResultSet resultado) {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}