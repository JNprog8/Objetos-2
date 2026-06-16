package punto2.model.authorization;

public class PermisoIntermedio implements Permiso {
    @Override
    public boolean tienePermiso(Permiso p) {
        return p.matchIntermedio();
    }

    @Override
    public boolean matchAdmin() {
        return false;
    }

    @Override
    public boolean matchIntermedio() {
        return true;
    }

    @Override
    public boolean matchBasico() {
        return false;
    }
}
