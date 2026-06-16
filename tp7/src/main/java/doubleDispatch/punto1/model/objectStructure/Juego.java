package doubleDispatch.punto1.model.objectStructure;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;
import doubleDispatch.punto1.model.concreteElement.Papel;
import doubleDispatch.punto1.model.concreteElement.Piedra;
import doubleDispatch.punto1.model.concreteElement.Tijera;
import doubleDispatch.punto1.model.dispatchLogic.Jugada;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            ElementoDeJuego eleccionUsuario = elegirElementoUsuario();
            if (eleccionUsuario == null) {
                System.out.println("Opción inválida. Inténtalo de nuevo.");
                continue;
            }

            ElementoDeJuego eleccionComputadora = elegirElementoComputadora();

            System.out.println("Tu elección: " + eleccionUsuario.getClass().getSimpleName().toLowerCase());
            System.out.println("Elección de la computadora: " + eleccionComputadora.getClass().getSimpleName().toLowerCase());

            System.out.println(new Jugada().jugar(eleccionUsuario, eleccionComputadora));

            continuar = preguntarSiQueremosContinuar();
        }

        System.out.println("¡Hasta pronto!");
        scanner.close();
    }

    private ElementoDeJuego elegirElementoUsuario() {
        System.out.println("Elige una opción: piedra, papel o tijera");
        String eleccion = scanner.nextLine().toLowerCase();
        return crearElemento(eleccion);
    }

    private ElementoDeJuego elegirElementoComputadora() {
        String[] opcionesStr = {"piedra", "papel", "tijera"};
        Random random = new Random();
        int indice = random.nextInt(opcionesStr.length);
        return crearElemento(opcionesStr[indice]);
    }

    private ElementoDeJuego crearElemento(String eleccion) {
        return switch (eleccion) {
            case "piedra" -> new Piedra();
            case "papel" -> new Papel();
            case "tijera" -> new Tijera();
            default -> null;
        };
    }

    private boolean preguntarSiQueremosContinuar() {
        System.out.println("¿Desea continuar? s/n");
        if (scanner.hasNextLine()) {
            String respuesta = scanner.nextLine();
            return !respuesta.isEmpty() && respuesta.toLowerCase().startsWith("s");
        }
        return false;
    }
}
