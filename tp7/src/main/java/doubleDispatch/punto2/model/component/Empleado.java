package doubleDispatch.punto2.model.component;

import doubleDispatch.punto2.model.composite.EmpleadoJerarquico;

public interface Empleado {
    float calcularSalario();

    boolean responderA(EmpleadoJerarquico supervisor);
}
