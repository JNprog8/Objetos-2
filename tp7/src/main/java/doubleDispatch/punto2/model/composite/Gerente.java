package doubleDispatch.punto2.model.composite;

import doubleDispatch.punto2.model.leaf.EmpleadoRegular;

public class Gerente extends EmpleadoJerarquico {
    public Gerente(String nombre, float salario) {
        super(nombre, salario);
    }

    @Override
    public boolean responderA(EmpleadoJerarquico supervisor) {
        return supervisor.supervizaAGerente(this);
    }

//    @Override
//    public boolean supervizaADirector(Director d) {
//        return false;
//    }

    @Override
    public boolean supervizaAGerente(Gerente g) {
        return false;
    }

    @Override
    public boolean supervizaAMandoMedio(MandoMedio m) {
        return true;
    }

    @Override
    public boolean supervizaALiderDeProyecto(LiderDeProyecto l) {
        return false;
    }

    @Override
    public boolean supervizaAEmpleadoRegular(EmpleadoRegular r) {
        return false;
    }
}
