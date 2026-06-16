package doubleDispatch.punto2.solucion2.model;

public class EmpleadoRegular implements Empleado {
    private final String nombre;
    private float salario;
    private Cargo cargo;

    public EmpleadoRegular(String nombre, float salario) {
        this.nombre = nombre;
        this.salario = salario;
        this.cargo = new Junior();
    }

    public float salario() {
        return this.salario;
    }

    @Override
    public float calcularSalario() {
        return this.salario;
    }

    @Override
    public Cargo cargo() {
        return cargo;
    }
}
