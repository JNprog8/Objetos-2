package punto1.model.composite;

import punto1.model.leaf.EmpleadoRegular;

public class LiderDeProyecto extends EmpleadoJerarquico {

    private static final String LIDER_DE_PROYECTO = "Líder de Proyecto";

    public LiderDeProyecto(String nombre, double salario) {
        super(nombre, salario);
    }

    @Override
    public String obtenerRol() {
        return LIDER_DE_PROYECTO;
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
