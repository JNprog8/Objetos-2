package punto1.model.leaf;

import punto1.model.component.Empleado;
import punto1.model.composite.EmpleadoJerarquico;

public class EmpleadoRegular implements Empleado {
    private static final String EMPLEADO_REGULAR = "Empleado Regular";

    private double salario;
    private String nombre;

    public EmpleadoRegular(String nombre, double salario) {
        this.salario = salario;
        this.nombre = nombre;
    }

    @Override
    public double calcularSalarioTotal() {
        return salario;
    }

    @Override
    public String obtenerRol() {
        return EMPLEADO_REGULAR;
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }
}
