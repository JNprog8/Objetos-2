package doubleDispatch.punto1.model.dispatchLogic;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;

public class Jugada {
    public static final String GANASTE = "¡Ganaste!";
    public static final String PERDISTE = "¡Perdiste!";
    public static final String EMPATE = "¡Es un empate!";

    public String jugar(ElementoDeJuego eleccionUsuario, ElementoDeJuego eleccionComputadora) {
        if (eleccionUsuario.empataCon(eleccionComputadora)) {
            return EMPATE;
        }
        if (eleccionUsuario.leGanaA(eleccionComputadora)) {
            return GANASTE;
        }
        return PERDISTE;
    }
}
