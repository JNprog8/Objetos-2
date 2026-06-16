package doubleDispatch.punto2.solucion2.model;

public class Director implements Cargo {

    @Override
    public boolean puedeSerJefeDe(Cargo cargo) {
        return cargo.puedeSerSubordinadoDeDirector();
    }

    @Override
    public boolean puedeSerSubordinadoDeDirector() {
        return false;
    }

    @Override
    public boolean puedeSerSubordinadoDeMandoMedio() {
        return false;
    }

    @Override
    public boolean puedeSerSubordinadorDeJunior() {
        return false;
    }

    @Override
    public boolean puedeSerJerarquico() {
        return true;
    }
}
