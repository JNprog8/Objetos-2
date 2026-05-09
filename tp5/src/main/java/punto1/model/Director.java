package punto1.model;

public class Director extends EmpleadoJerarquico {

    public Director(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return "Director";
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }

    @Override
    public void recibeA(Gerente emp) {
        this.confirmarSubordinado(emp);
    }
}
