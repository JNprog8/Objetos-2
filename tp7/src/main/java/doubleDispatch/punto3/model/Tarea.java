package doubleDispatch.punto3.model;

public class Tarea implements ItemDeProyecto {
    private int horasEstimadas;

    public Tarea(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    @Override
    public int horasTotales() {
        return this.horasEstimadas;
    }

    @Override
    public boolean validarEn(TareaCompleja contenedor) {
        return contenedor.validarTarea(this);
    }
}
