package punto1.model.composite;

public class Gerente extends EmpleadoJerarquico {

    private static final String GERENTE = "Gerente";

    public Gerente(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return GERENTE;
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }

    @Override
    public void recibeA(MandoMedio emp) {
        this.confirmarSubordinado(emp);
    }
}
