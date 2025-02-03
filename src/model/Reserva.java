package model;

import exceptions.AsientoOcupadoException;

/**
 *
 * @author polmi
 */
public class Reserva {

    private int id;
    private Usuario usuario;
    private Vuelo vuelo;
    private Asiento asiento;
    private String fechaHora;
    private double precio;

    public Reserva() {
    }

    public Reserva(int id, Usuario usuario, Vuelo vuelo, Asiento asiento, String fechaHora, double precio) {
        this.id = id;
        this.usuario = usuario;
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.fechaHora = fechaHora;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //Metodos
    public void confirmarReserva() throws AsientoOcupadoException {
        if (asiento.estaDisponible()) {
            asiento.ocupar();
            System.out.println("Reserva confirmada para " + usuario.getNombre());
        } else {
            System.out.println("El asiento ya esta ocupado.");
        }
    }

    public void cancelarReserva() {
        asiento.liberar();
        System.out.println("Reserva cancelada para " + usuario.getNombre());
    }
    
    public String generarBillete() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== BILLETE DE VUELO ==========\n");
        sb.append("Reserva ID: ").append(id).append("\n");
        sb.append("Pasajero: ").append(usuario.getNombre()).append(" ").append(usuario.getApellidos()).append("\n");
        sb.append("DNI: ").append(usuario.getDni()).append("\n");
        sb.append("Vuelo ID: ").append(vuelo.getId()).append("\n");
        sb.append("Origen: ").append(vuelo.getOrigen()).append("\n");
        sb.append("Destino: ").append(vuelo.getDestino()).append("\n");
        sb.append("Fecha y hora del vuelo: ").append(vuelo.getFechaHora()).append("\n");
        sb.append("Avion: ").append(vuelo.getAvion().getModelo()).append("\n");
        sb.append("Asiento: ").append(asiento.getNumAsiento()).append(" - Clase: ").append(asiento.getClase()).append("\n");
        sb.append("Precio: ").append(precio).append(" EUR\n");
        sb.append("======================================\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva [id=").append(id)
                .append(", usuario=").append(usuario)
                .append(", vuelo=").append(vuelo)
                .append(", asiento=").append(asiento)
                .append(", fechaHora=").append(fechaHora)
                .append(", precio=").append(precio)
                .append("]");
        return sb.toString();
    }
    
}
