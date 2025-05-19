package model;

import enums.ClaseAsiento;
import enums.OrigenDestino;
import java.util.ArrayList;
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
    private List<Asiento> asientos;

    public Vuelo() {
    }

    public Vuelo(int id, OrigenDestino origen, OrigenDestino destino, String fechaHora, Avion avion, List<Asiento> asientos) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.avion = avion;
        this.asientos = asientos;
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

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    //Metodos
    public void mostrarAsientos() {
        System.out.println("Asientos en el vuelo con id = " + id + ":");
        for (Asiento asiento : asientos) {
            System.out.println(asiento);
        }
    }

    public void mostrarDetalles() {
        System.out.println("Vuelo [id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaHora=" + fechaHora + "]");
        mostrarAsientos();
    }

    public void mostrarAsientosDisponibles(ClaseAsiento clase) {
        System.out.println("Asientos disponibles en clase " + clase + ":");
        List<Asiento> disponibles = buscarAsientoDisponiblePorClase(clase);
        for (Asiento asiento : disponibles) {
            System.out.println(asiento);
        }
    }

    public List<Asiento> buscarAsientoDisponiblePorClase(ClaseAsiento clase) {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento asiento : asientos) {
            if (asiento.getClase() == clase && asiento.estaDisponible()) {
                disponibles.add(asiento);
            }
        }
        return disponibles;
    }

    public List<Integer> obtenerAsientosDisponibles(ClaseAsiento clase) {
        List<Integer> asientosDisponibles = new ArrayList<>();
        for (Asiento asiento : getAsientos()) {
            if (asiento.getClase() == clase && !asiento.isOcupado()) {
                asientosDisponibles.add(asiento.getNumAsiento());
            }
        }
        return asientosDisponibles;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vuelo [id=").append(id)
                .append(", origen=").append(origen)
                .append(", destino=").append(destino)
                .append(", fechaHora=").append(fechaHora)
                .append(", avion=").append(avion)
                .append("asientos=").append(asientos)
                .append("]");
        return sb.toString();
    }

}
