package model;

/**
 *
 * @author polmi
 */
public class Usuario {

    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String dni;
    private String telefono;
    private String direccion;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos, String email, String dni, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        setDni(dni); // Validación al asignar el DNI
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (esDniValido(dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("DNI inválido: " + dni);
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Verifica si el dni és válido
     * @param dni
     * @return 
     */
    private boolean esDniValido(String dni) {
        if (dni == null || !dni.matches("\\d{8}[A-Z]")) {
            return false;
        }

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeros = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letras.charAt(numeros % 23);
        char letraProporcionada = dni.charAt(8);

        return letraCalculada == letraProporcionada;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario [id=").append(id)
                .append(", nombre=").append(nombre)
                .append(", apellidos=").append(apellidos)
                .append(", email=").append(email)
                .append(", dni=").append(dni)
                .append(", telefono=").append(telefono)
                .append(", direccion=").append(direccion)
                .append("]");
        return sb.toString();
    }
}