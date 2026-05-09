package punto1.model;

public class Gerente extends EmpleadoJerarquico {

    public Gerente(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return "Gerente";
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
