package doubleDispatch.punto1.model.concreteElement;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;

public class Piedra extends ElementoDeJuego {
    @Override
    public boolean leGanaA(ElementoDeJuego eleccionComputadora) {
        return eleccionComputadora.pierdeContraPiedra();
    }

    @Override
    public boolean pierdeContraTijera() {
        return false;
    }

    @Override
    public boolean pierdeContraPiedra() {
        return false;
    }

    @Override
    public boolean pierdeContraPapel() {
        return true;
    }
}
