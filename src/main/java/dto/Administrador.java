package dto;

public class Administrador {

	//Atributos
    private int idAdmin;
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasenia;

    // Constructor vacío
    public Administrador() {
    }

    // Constructor con todos los atributos
    public Administrador(int idAdmin, int idUsuario, String nombre, String email, String contrasenia) {
		super();
		this.idAdmin = idAdmin;
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
	}

	// Getters y Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    public int getIdUsuario() {
		return idUsuario;
	}
    
    public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
