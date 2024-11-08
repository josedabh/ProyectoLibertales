package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para cerrar la conexión a la base de datos.
 */
public class CerrarConexion {
    private Connection con;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    // Constructor que recibe la conexión, la sentencia y el resultado
    public CerrarConexion(Connection con, PreparedStatement sentencia, ResultSet resultado) {
        this.con = con;
        this.sentencia = sentencia;
        this.resultado = resultado;
    }

    /**
     * Cierra la conexión, la sentencia y el resultado de la base de datos.
     */
    public void cerrar() {
        try {
            if (resultado != null) {
                resultado.close();  
                System.out.println("ResultSet cerrado");
            }
            if (sentencia != null) {
                sentencia.close();  
                System.out.println("Sentencia cerrada");
            }
            if (con != null) {
                con.close();  
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}