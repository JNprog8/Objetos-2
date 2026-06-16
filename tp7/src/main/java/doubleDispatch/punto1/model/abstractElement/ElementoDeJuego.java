package doubleDispatch.punto1.model.abstractElement;

public abstract class ElementoDeJuego {
    public abstract boolean leGanaA(ElementoDeJuego eleccionComputadora);

    public abstract boolean pierdeContraPapel();

    public abstract boolean pierdeContraPiedra();

    public abstract boolean pierdeContraTijera();

    public boolean empataCon(ElementoDeJuego eleccionComputadora) {
        return this.getClass().equals(eleccionComputadora.getClass());
    }
}
