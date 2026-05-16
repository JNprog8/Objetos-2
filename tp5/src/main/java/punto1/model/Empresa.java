package punto1.model;

import punto1.model.component.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Empleado> empleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public double calcularTotalSalarial() {
        return empleados.stream()
                .mapToDouble(Empleado::calcularSalarioTotal)
                .sum();
    }
}
