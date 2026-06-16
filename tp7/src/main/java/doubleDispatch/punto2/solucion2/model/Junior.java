package doubleDispatch.punto2.solucion2.model;

public class Junior implements Cargo {

    @Override
    public boolean puedeSerJefeDe(Cargo cargo) {
        return cargo.puedeSerSubordinadorDeJunior();
    }

    @Override
    public boolean puedeSerSubordinadoDeDirector() {
        return false;
    }

    @Override
    public boolean puedeSerSubordinadoDeMandoMedio() {
        return true;
    }

    @Override
    public boolean puedeSerSubordinadorDeJunior() {
        return false;
    }

    @Override
    public boolean puedeSerJerarquico() {
        return false;
    }
}
