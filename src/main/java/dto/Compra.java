package dto;

import java.util.Date;

public class Compra {
    private int idCompra;
    private int idLector;
    private int idLibro;
    private Date fechaCompra;

    // Constructor
    public Compra(int idCompra, int idLector, int idLibro, Date fechaCompra) {
        this.idCompra = idCompra;
        this.idLector = idLector;
        this.idLibro = idLibro;
        this.fechaCompra = fechaCompra;
    }

    // Getters y Setters
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
