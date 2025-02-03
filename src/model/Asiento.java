package model;

import enums.ClaseAsiento;
import exceptions.AsientoOcupadoException;

/**
 *
 * @autor polmi
 */
public class Asiento {

    private int numAsiento;
    private ClaseAsiento clase;
    private boolean estado; // true = ocupado, false = libre

    public Asiento() {
    }

    public Asiento(int numAsiento, ClaseAsiento clase, boolean estado) {
        this.numAsiento = numAsiento;
        this.clase = clase;
        this.estado = estado;
    }

    // Métodos adicionales
    public void ocupar() throws AsientoOcupadoException {
        if (!estado) {
            estado = true;
        } else {
            throw new AsientoOcupadoException("El asiento ya está ocupado.");
        }
    }

    public void liberar() {
        if (estado) {
            estado = false;
        } else {
            System.out.println("El asiento ya está libre.");
        }
    }
    
    public boolean estaDisponible() {
        return !estado;
    }

    // Getters y Setters mejorados
    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        if (numAsiento > 0) {
            this.numAsiento = numAsiento;
        } else {
            System.out.println("El número de asiento debe ser positivo.");
        }
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public void setClase(ClaseAsiento clase) {
        this.clase = clase;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asiento [numAsiento=").append(numAsiento)
                .append(", clase=").append(clase)
                .append(", estado=").append(estado)
                .append("]");
        return sb.toString();
    }
}