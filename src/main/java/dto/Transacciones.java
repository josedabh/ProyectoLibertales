package dto;

import java.sql.Date;

public class Transacciones {

	// Atributos
	int id_transaccion;
	int id_libro;
	int id_lector;
	public enum tipo_transaccion{
		Venta,
		Alquiler;
	}
	Date fecha_inicio;
	Date fecha_fin;
	double precio;
	

	// Constructor parametrizado
	public Transacciones(int id_transaccion, int id_libro, int id_lector, Date fecha_inicio, Date fecha_fin,
			double precio) {
		this.id_transaccion = id_transaccion;
		this.id_libro = id_libro;
		this.id_lector = id_lector;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.precio = precio;
	}
	
	
	// Getters Y Setters
	public int getId_transaccion() {
		return id_transaccion;
	}


	public void setId_transaccion(int id_transaccion) {
		this.id_transaccion = id_transaccion;
	}


	public int getId_libro() {
		return id_libro;
	}


	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}


	public int getId_lector() {
		return id_lector;
	}


	public void setId_lector(int id_lector) {
		this.id_lector = id_lector;
	}


	public Date getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public Date getFecha_fin() {
		return fecha_fin;
	}


	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}
}