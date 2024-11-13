package dto;

public class Usuario {

	//Atributos
    private int idUsuario;
    private String email;
    private String contrasenia;
    private String tipo;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con todos los atributos
    public Usuario(int idUsuario, String email, String contrasenia, String tipo) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", email=" + email + ", contrasenia=" + contrasenia + ", tipo="
				+ tipo + "]";
	}
    
    
}
