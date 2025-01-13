package model;

import enums.ClaseAsiento;
import enums.OrigenDestino;
import java.util.List;

/**
 *
 * @author polmi
 */
public class Vuelo {

    private int id;
    private OrigenDestino origen;
    private OrigenDestino destino;
    private String fechaHora;
    private Avion avion;

    public Vuelo() {
    }

    public Vuelo(int id, OrigenDestino origen, OrigenDestino destino, String fechaHora, Avion avion) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.avion = avion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrigenDestino getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenDestino origen) {
        this.origen = origen;
    }

    public OrigenDestino getDestino() {
        return destino;
    }

    public void setDestino(OrigenDestino destino) {
        this.destino = destino;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    
    //Metodos
    public void mostrarDetalles() {
        System.out.println("Vuelo [id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaHora=" + fechaHora + "]");
        avion.mostrarAsientos();
    }

    public void mostrarAsientosDisponibles(ClaseAsiento clase) {
        System.out.println("Asientos disponibles en clase " + clase + ":");
        List<Asiento> disponibles = avion.buscarAsientoDisponiblePorClase(clase);
        for (Asiento asiento : disponibles) {
            System.out.println(asiento);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vuelo [id=").append(id)
                .append(", origen=").append(origen)
                .append(", destino=").append(destino)
                .append(", fechaHora=").append(fechaHora)
                .append(", avion=").append(avion)
                .append("]");
        return sb.toString();
    }
}
