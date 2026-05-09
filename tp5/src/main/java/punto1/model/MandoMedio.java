package punto1.model;

public class MandoMedio extends EmpleadoJerarquico {

    public MandoMedio(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return "Mando Medio";
    }

    @Override
    public void respondeA(EmpleadoJerarquico jefe) {
        jefe.recibeA(this);
    }

    @Override
    public void recibeA(LiderDeProyecto emp) {
        this.confirmarSubordinado(emp);
    }
}
