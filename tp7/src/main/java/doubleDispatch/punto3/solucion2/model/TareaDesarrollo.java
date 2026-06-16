package doubleDispatch.punto3.solucion2.model;

public class TareaDesarrollo implements TipoTarea {
    @Override
    public boolean puedeContenerA(TipoTarea tipoTarea) {
        return false;
    }

    @Override
    public boolean puedeSerContenidaPorHistoriaDeUsuario() {
        return true;
    }

    @Override
    public boolean puedeSerContenidaPorEpica() {
        return false;
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
