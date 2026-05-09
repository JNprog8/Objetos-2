package punto1.model;

public class EmpleadoRegular implements Empleado {
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
        return "Empleado Regular";
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }
}
