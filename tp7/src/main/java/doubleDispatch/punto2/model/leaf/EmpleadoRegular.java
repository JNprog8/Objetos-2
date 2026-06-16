package doubleDispatch.punto2.model.leaf;

import doubleDispatch.punto2.model.component.Empleado;
import doubleDispatch.punto2.model.composite.EmpleadoJerarquico;

public class EmpleadoRegular implements Empleado {
    private String nombre;
    private float salario;

    public EmpleadoRegular(String nombre, float salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    @Override
    public float calcularSalario() {
        return this.salario;
    }

    @Override
    public boolean responderA(EmpleadoJerarquico supervisor) {
        return supervisor.supervizaAEmpleadoRegular(this);
    }
}
