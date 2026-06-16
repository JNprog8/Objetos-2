package doubleDispatch.punto2.solucion2.model;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoJerarquico implements Empleado {
    public static final String ASIGNACION_EMPLEADO_INVALIDA = "No puede agregar como subordinado a este empleado";
    public static final String VALIDA_EMPLEADO_JERARQUICO = "Solo directores o mandos medios pueden ser jerárquico";
    private final String nombre;
    private List<Empleado> empleados;
    private float salario;
    private Cargo cargo;

    public EmpleadoJerarquico(String nombre, float salario, Cargo cargo) {
        if (!cargo.puedeSerJerarquico()) {
            throw new RuntimeException(VALIDA_EMPLEADO_JERARQUICO);
        }
        this.nombre = nombre;
        this.salario = salario;
        this.cargo = cargo;
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        if (!this.cargo.puedeSerJefeDe(empleado.cargo())) {
            throw new RuntimeException(ASIGNACION_EMPLEADO_INVALIDA);
        }
        this.empleados.add(empleado);
    }

    @Override
    public float calcularSalario() {
        float total = this.salario;
        for (Empleado m : empleados) {
            total += m.calcularSalario();
        }
        return total;
    }

    @Override
    public Cargo cargo() {
        return this.cargo;
    }

    public boolean tieneDeEmpeadoA(Empleado mandoMedio) {
        return this.empleados.contains(mandoMedio);
    }
}
