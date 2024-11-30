package dto;

public class HistorialAlquiler {
	
	//Atributos
	private String nomLector;
	private String tituloLibro;
	private String fechaInicio;
	private String fechaLimite;
	
	//Constructor
	public HistorialAlquiler(String nomLector, String tituloLibro, String fechaInicio, String fechaLimite) {
		super();
		this.nomLector = nomLector;
		this.tituloLibro = tituloLibro;
		this.fechaInicio = fechaInicio;
		this.fechaLimite = fechaLimite;
	}

	//Getters y Setters
	public String getNomLector() {
		return nomLector;
	}

	public void setNomLector(String nomLector) {
		this.nomLector = nomLector;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
	

}
