package doubleDispatch.punto1.model.concreteElement;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;

public class Papel extends ElementoDeJuego {
    @Override
    public boolean leGanaA(ElementoDeJuego eleccionComputadora) {
        return eleccionComputadora.pierdeContraPapel();
    }

    @Override
    public boolean pierdeContraPapel() {
        return false;
    }

    @Override
    public boolean pierdeContraPiedra() {
        return false;
    }

    @Override
    public boolean pierdeContraTijera() {
        return true;
    }
}
