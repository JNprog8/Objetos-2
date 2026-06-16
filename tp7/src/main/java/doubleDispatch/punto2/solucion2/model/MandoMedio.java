package doubleDispatch.punto2.solucion2.model;

public class MandoMedio implements Cargo {
    @Override
    public boolean puedeSerJefeDe(Cargo cargo) {
        return cargo.puedeSerSubordinadoDeMandoMedio();
    }

    @Override
    public boolean puedeSerSubordinadoDeDirector() {
        return true;
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