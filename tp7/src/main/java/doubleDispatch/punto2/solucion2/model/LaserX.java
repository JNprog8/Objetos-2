package doubleDispatch.punto2.solucion2.model;

public class LaserX {

    private Empleado directorGeneral;

    public LaserX(Empleado directorGeneral) {
        this.directorGeneral = directorGeneral;
    }

    public float masaSalarial() {
        return directorGeneral.calcularSalario();
    }

    public void imprimirCostoSalarial() {
        System.out.println(masaSalarial());
    }
}
