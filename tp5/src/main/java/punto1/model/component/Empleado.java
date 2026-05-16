package punto1.model.component;

import punto1.model.composite.EmpleadoJerarquico;

public interface Empleado {

    double calcularSalarioTotal();

    String obtenerRol();

    void respondeA(EmpleadoJerarquico jefe);
}

