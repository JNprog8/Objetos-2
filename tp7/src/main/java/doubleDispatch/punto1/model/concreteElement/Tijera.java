package doubleDispatch.punto1.model.concreteElement;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;

public class Tijera extends ElementoDeJuego {
    @Override
    public boolean leGanaA(ElementoDeJuego eleccionComputadora) {
        return eleccionComputadora.pierdeContraTijera();
    }

    @Override
    public boolean pierdeContraPapel() {
        return false;
    }

    @Override
    public boolean pierdeContraTijera() {
        return false;
    }

    @Override
    public boolean pierdeContraPiedra() {
        return true;
    }
}
