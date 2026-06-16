package doubleDispatch.punto3.solucion2.model;

public class Epica implements TipoTarea {
    @Override
    public boolean puedeContenerA(TipoTarea tipoTarea) {
        return tipoTarea.puedeSerContenidaPorEpica();
    }

    @Override
    public boolean puedeSerContenidaPorHistoriaDeUsuario() {
        return false;
    }

    @Override
    public boolean puedeSerContenidaPorEpica() {
        return false;
    }

    @Override
    public boolean puedeSerCompleja() {
        return true;
    }

    @Override
    public boolean puedeSerSimple() {
        return false;
    }
}
