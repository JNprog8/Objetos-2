package doubleDispatch.punto2.solucion2.model;

public interface Cargo {
    boolean puedeSerJefeDe(Cargo cargo);

    boolean puedeSerSubordinadoDeDirector();

    boolean puedeSerSubordinadoDeMandoMedio();

    boolean puedeSerSubordinadorDeJunior();

    boolean puedeSerJerarquico();
}
