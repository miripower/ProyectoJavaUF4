
package view.console;

import enums.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author polmi
 */
public class Main {
    public static void main(String[] args) {
        // Crear usuarios
        Usuario usuario1 = new Usuario(1, "Juan", "Perez", "juan.perez@example.com", "12345678Z", "600123456", "Calle Falsa 123");
        Usuario usuario2 = new Usuario(2, "Ana", "Lopez", "ana.lopez@example.com", "87654321X", "610987654", "Avenida Siempre Viva 742");

        // Crear asientos
        List<Asiento> asientos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ClaseAsiento clase = (i <= 5) ? ClaseAsiento.ECONOMICA : ClaseAsiento.PREMIUM;
            asientos.add(new Asiento(i, clase, false));
        }

        // Crear avión
        Avion avion = new Avion(1, "Boeing 737", 10, asientos);

        // Crear vuelo
        Vuelo vuelo = new Vuelo(1, OrigenDestino.MADRID, OrigenDestino.BARCELONA, "2024-12-20 15:30", avion);

        // Mostrar detalles del vuelo y los asientos
        vuelo.mostrarDetalles();

        // Crear reservas
        Reserva reserva1 = new Reserva(1, usuario1, vuelo, asientos.get(0), "2024-12-15 10:00", 150.00);
        Reserva reserva2 = new Reserva(2, usuario2, vuelo, asientos.get(5), "2024-12-15 11:00", 200.00);

        // Confirmar reservas
        System.out.println("\nConfirmando reservas...");
        reserva1.confirmarReserva();
        reserva2.confirmarReserva();

        // Generar billetes
        System.out.println("\nGenerando billetes...");
        System.out.println(reserva1.generarBillete());
        System.out.println(reserva2.generarBillete());

        // Cancelar una reserva
        System.out.println("\nCancelando reserva...");
        reserva1.cancelarReserva();

        // Mostrar asientos disponibles en clase económica nuevamente
        System.out.println("\nAsientos disponibles en clase económica despues de cancelar:");
        vuelo.mostrarAsientosDisponibles(ClaseAsiento.ECONOMICA);
    }
}