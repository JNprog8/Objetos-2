package resolucion.punto5;

public class SecuritySubSystem {
    public static final String NUMERO = "1";
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";

    public void ejecutarConPermiso(String userId, Accion accion) {
        this.validarPermiso(userId);
        accion.ejecutar();
    }

    public <T> T ejecutarConPermiso(String userId, Consulta<T> consulta) {
        this.validarPermiso(userId);
        return consulta.ejecutar();
    }

    private void validarPermiso(String userId) {
        if (!this.tienePermiso(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
    }

    private boolean tienePermiso(String userId) {
        return NUMERO.equals(userId);//  ;)
    }
}
