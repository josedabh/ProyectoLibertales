package dto;

public class Libro {

	// Atributos
	int id_libro;
	String titulo;
	String autor;
	String editorial;
	int anio_publicaion;
	double precioCompra;
	double precioAlquiler;
	String sinopsis;
	String rutaImagen;
	

	// Constructor parametrizado
	public Libro(int id_libro, String titulo, String autor, String editorial, int anio_publicaion, double precioCompra,
			double precioAlquiler, String sinopsis, String rutaImagen) {
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.anio_publicaion = anio_publicaion;
		this.precioCompra = precioCompra;
		this.precioAlquiler = precioAlquiler;
		this.sinopsis = sinopsis;
		this.rutaImagen = rutaImagen;
	}
	
	
	// Getters Y Setters
	public int getId_libro() {
		return id_libro;
	}


	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public int getAnio_publicaion() {
		return anio_publicaion;
	}


	public void setAnio_publicaion(int anio_publicaion) {
		this.anio_publicaion = anio_publicaion;
	}


	public double getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}


	public double getPrecioAlquiler() {
		return precioAlquiler;
	}


	public void setPrecioAlquiler(double precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public String getRutaImagen() {
		return rutaImagen;
	}


	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
}