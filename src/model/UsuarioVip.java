package model;

/**
 *
 * @author polmi
 */
public class UsuarioVip extends Usuario {

    private double descuentos;
    private boolean salavip;

    public UsuarioVip() {
    }

    public UsuarioVip(int id, String nombre, String apellidos, String email, String dni, String telefono, String direccion, double descuentos, boolean salavip) {
        super(id, nombre, apellidos, email, dni, telefono, direccion);
        this.descuentos = descuentos;
        this.salavip = salavip;
    }

    // Getters y Setters
    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public boolean isSalavip() {
        return salavip;
    }

    public void setSalavip(boolean salavip) {
        this.salavip = salavip;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append(", descuentos=").append(descuentos)
                .append(", salavip=").append(salavip)
                .append("]");
        return sb.toString();
    }
}
