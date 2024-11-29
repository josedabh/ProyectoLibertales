package dto;

import java.time.LocalDate;

public class Alquiler {
	
	// Atributos
	private int idAlquiler;
    private int idLector;
    private int idLibro;
    private java.time.LocalDate fechaInicio;
    private java.time.LocalDate fechaLimite;
    
    // Constructor
	public Alquiler(int idAlquiler, int idLector, int idLibro, LocalDate fechaInicio, LocalDate fechaLimite) {
		this.idAlquiler = idAlquiler;
		this.idLector = idLector;
		this.idLibro = idLibro;
		this.fechaInicio = fechaInicio;
		this.fechaLimite = fechaLimite;
	}

	// Getters y setters
	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
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

	public java.time.LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(java.time.LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public java.time.LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(java.time.LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	
    
}
