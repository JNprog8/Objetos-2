package punto1.model;

public class LiderDeProyecto extends EmpleadoJerarquico {

    public LiderDeProyecto(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return "Líder de Proyecto";
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }

    @Override
    public void recibeA(EmpleadoRegular emp) {
        this.confirmarSubordinado(emp);
    }
}
