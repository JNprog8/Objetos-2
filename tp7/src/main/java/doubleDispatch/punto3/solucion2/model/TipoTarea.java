package doubleDispatch.punto3.solucion2.model;

public interface TipoTarea {
    boolean puedeContenerA(TipoTarea tipoTarea);

    boolean puedeSerContenidaPorHistoriaDeUsuario();

    boolean puedeSerContenidaPorEpica();

    boolean puedeSerCompleja();

    boolean puedeSerSimple();
}

