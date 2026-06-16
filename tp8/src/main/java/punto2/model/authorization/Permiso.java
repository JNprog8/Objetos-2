package punto2.model.authorization;

public interface Permiso {
    boolean tienePermiso(Permiso permiso);

    boolean matchAdmin();

    boolean matchIntermedio();

    boolean matchBasico();
}
