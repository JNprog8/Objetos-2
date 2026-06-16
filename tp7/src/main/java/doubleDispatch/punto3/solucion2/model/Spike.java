package doubleDispatch.punto3.solucion2.model;

public class Spike implements TipoTarea {
    @Override
    public boolean puedeContenerA(TipoTarea tipoTarea) {
        return false;
    }

    @Override
    public boolean puedeSerContenidaPorHistoriaDeUsuario() {
        return false;
    }

    @Override
    public boolean puedeSerContenidaPorEpica() {
        return true;
    }

    @Override
    public boolean puedeSerCompleja() {
        return false;
    }

    @Override
    public boolean puedeSerSimple() {
        return true;
    }
}
