package dto;

public class Lector {

	//Atributos
    private int idLector;
    private int idusuario;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String contrasenia;

    // Constructor vacío
    public Lector() {
    }
    
    // Constructor con todos los atributos
    public Lector(int idLector, int idusuario, String nombre, String direccion, String telefono, String email,
			String contrasenia) {
		super();
		this.idLector = idLector;
		this.idusuario = idusuario;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.contrasenia = contrasenia;
	}

	// Getters y Setters
    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public int getIdusuario() {
		return idusuario;
	}
    
    public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
