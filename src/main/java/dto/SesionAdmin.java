package dto;

public class SesionAdmin {
	
    private static SesionAdmin instancia;
    private Integer idAdmin;

    private SesionAdmin() {}

    public static SesionAdmin getInstancia() {
        if (instancia == null) {
            instancia = new SesionAdmin();
        }
        return instancia;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    // Método para cerrar la sesión
    public void cerrarSesion() {
        idAdmin = null;
    }
}