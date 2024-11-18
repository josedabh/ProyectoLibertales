package dto;

public class SesionUsuario {
	
    private static SesionUsuario instancia;
    private Integer idLector;

    private SesionUsuario() {}

    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public Integer getIdLector() {
        return idLector;
    }

    public void setIdLector(Integer idLector) {
        this.idLector = idLector;
    }

	// Método para cerrar la sesión
    public void cerrarSesion() {
        idLector = null;
    }
}
