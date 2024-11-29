package dto;

public class Favorito {
	
	// Atributos
    private int idLector;
    private int idLibro;
    
    // Constructor
	public Favorito(int idLector, int idLibro) {
		this.idLector = idLector;
		this.idLibro = idLibro;
	}

	// Getters y setters
	public int getIdLector() {
		return idLector;
	}

	public void setIdLector(int idLector) {
		this.idLector = idLector;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	
}
