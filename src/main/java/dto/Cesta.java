package dto;

public class Cesta {
    private int idLector;
    private int idLibro;
    
	public Cesta(int idLector, int idLibro) {
		this.idLector = idLector;
		this.idLibro = idLibro;
	}

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
