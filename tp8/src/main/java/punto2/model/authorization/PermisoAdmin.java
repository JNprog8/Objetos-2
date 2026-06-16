package punto2.model.authorization;

public class PermisoAdmin implements Permiso {
    @Override
    public boolean tienePermiso(Permiso p) {
        return p.matchAdmin();
    }

    @Override
    public boolean matchAdmin() {
        return true;
    }

    @Override
    public boolean matchIntermedio() {
        return false;
    }

    @Override
    public boolean matchBasico() {
        return false;
    }
}
