package punto2.model;

import punto2.model.authorization.Permiso;

import java.util.List;

public class Usuario {
    private String name;
    private List<Permiso> permisos;

    public Usuario(String name, List<Permiso> permisos) {
        this.name = name;
        this.permisos = permisos;
    }

    // deberia ser protected, lo conservo public por el main
    public boolean poseePermiso(Permiso permiso) {
        return permisos.stream().anyMatch(pQueTengo -> pQueTengo.tienePermiso(permiso));
    }
}
