package punto1.model;

import java.util.ArrayList;
import java.util.List;

public abstract class EmpleadoJerarquico implements Empleado {
    private double salario;
    private String nombre;
    private List<Empleado> subordinados;

    public EmpleadoJerarquico(String nombre, double salario) {
        this.salario = salario;
        this.nombre = nombre;
        this.subordinados = new ArrayList<>();
    }

    public void asignarSubordinado(Empleado subordinado) {
        subordinado.respondeA(this);
    }

    protected void confirmarSubordinado(Empleado subordinado) {
        this.subordinados.add(subordinado);
    }

    @Override
    public double calcularSalarioTotal() {
        return salario + subordinados.stream().mapToDouble(Empleado::calcularSalarioTotal).sum();
    }

    public void recibeA(EmpleadoRegular emp) {
        rechazar(emp);
    }

    public void recibeA(LiderDeProyecto emp) {
        rechazar(emp);
    }

    public void recibeA(MandoMedio emp) {
        rechazar(emp);
    }

    public void recibeA(Gerente emp) {
        rechazar(emp);
    }

    public void recibeA(Director emp) {
        rechazar(emp);
    }

    private void rechazar(Empleado subordinado) {
        throw new RuntimeException("Un [" + subordinado.obtenerRol() + "] no puede reportar a un [" + this.obtenerRol() + "]");
    }
}
