package punto1.model.composite;

public class MandoMedio extends EmpleadoJerarquico {

    private static final String MANDO_MEDIO = "Mando Medio";

    public MandoMedio(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return MANDO_MEDIO;
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
