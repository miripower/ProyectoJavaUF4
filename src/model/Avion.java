package model;

import enums.ClaseAsiento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author polmi
 */
public class Avion {

    private int id;
    private String modelo;
    private int capacidad;
    private List<Asiento> asientos;

    public Avion() {
        this.asientos = new ArrayList<>();
    }

    public Avion(int id, String modelo, int capacidad, List<Asiento> asientos) {
        this.id = id;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.asientos = asientos != null ? asientos : new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    // Métodos adicionales
    public List<Asiento> buscarAsientoDisponiblePorClase(ClaseAsiento clase) {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento asiento : asientos) {
            if (asiento.getClase() == clase && asiento.estaDisponible()) {
                disponibles.add(asiento);
            }
        }
        return disponibles;
    }

    public void mostrarAsientos() {
        System.out.println("Asientos en el avion " + modelo + ":");
        for (Asiento asiento : asientos) {
            System.out.println(asiento);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Avion [id=").append(id)
                .append(", modelo=").append(modelo)
                .append(", capacidad=").append(capacidad)
                .append(", asientos=").append(asientos)
                .append("]");
        return sb.toString();
    }
}
