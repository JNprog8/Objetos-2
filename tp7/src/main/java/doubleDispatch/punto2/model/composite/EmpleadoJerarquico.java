package doubleDispatch.punto2.model.composite;

import doubleDispatch.punto2.model.component.Empleado;
import doubleDispatch.punto2.model.leaf.EmpleadoRegular;

import java.util.ArrayList;
import java.util.List;

public abstract class EmpleadoJerarquico implements Empleado {
    public static final String ASIGNACION_EMPLEADO_INVALIDA = "Violación de Jerarquía";
    protected String nombre;
    protected float salario;
    protected List<Empleado> subordinados;

    public EmpleadoJerarquico(String nombre, float salario) {
        this.nombre = nombre;
        this.salario = salario;
        this.subordinados = new ArrayList<>();
    }

    public boolean tieneDeEmpeadoA(Empleado subordinado) {
        return this.subordinados.contains(subordinado);
    }

    public void agregarSubordinado(Empleado subordinado) {
        if (!subordinado.responderA(this)) {
            throw new RuntimeException(ASIGNACION_EMPLEADO_INVALIDA + ": " + this.getClass().getSimpleName() +
                    " no puede supervisar a " + subordinado.getClass().getSimpleName());
        }
        this.subordinados.add(subordinado);
    }

    @Override
    public float calcularSalario() {
        return this.salario + (float) subordinados.stream()
                .mapToDouble(Empleado::calcularSalario)
                .sum();
    }

    // Si en algun caso Un director 'General' supervisa a otros directores -> descomentamos e implementamos 'true' en director
    //public abstract boolean supervizaADirector(Director director);

    public abstract boolean supervizaAGerente(Gerente gerente);

    public abstract boolean supervizaAMandoMedio(MandoMedio mando);

    public abstract boolean supervizaALiderDeProyecto(LiderDeProyecto lider);

    public abstract boolean supervizaAEmpleadoRegular(EmpleadoRegular regular);
}
