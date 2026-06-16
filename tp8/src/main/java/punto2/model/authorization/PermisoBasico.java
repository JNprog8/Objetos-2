package punto2.model.authorization;

public class PermisoBasico implements Permiso {
    @Override
    public boolean tienePermiso(Permiso p) {
        return p.matchBasico();
    }

    @Override
    public boolean matchAdmin() {
        return false;
    }

    @Override
    public boolean matchIntermedio() {
        return false;
    }

    @Override
    public boolean matchBasico() {
        return true;
    }
}
