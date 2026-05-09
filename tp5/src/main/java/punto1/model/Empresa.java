package punto1.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Empleado> empleadosPrincipales;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleadosPrincipales = new ArrayList<>();
    }

    public void agregarEmpleadoPrincipal(Empleado empleado) {
        this.empleadosPrincipales.add(empleado);
    }

    public double montoTotalSalarial() {
        return empleadosPrincipales.stream()
                .mapToDouble(Empleado::calcularSalarioTotal)
                .sum();
    }
}
