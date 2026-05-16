package punto1.model.composite;

public class Director extends EmpleadoJerarquico {

    private static final String DIRECTOR = "Director";

    public Director(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return DIRECTOR;
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
