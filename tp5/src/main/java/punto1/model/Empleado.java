package punto1.model;

public interface Empleado {

    double calcularSalarioTotal();

    String obtenerRol();

    void respondeA(EmpleadoJerarquico jefe);
}

