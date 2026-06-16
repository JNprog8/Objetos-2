package doubleDispatch.punto3.model;

public class Spike implements ItemDeProyecto {
    private int horasEstimadas;

    public Spike(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    @Override
    public int horasTotales() {
        return this.horasEstimadas;
    }

    @Override
    public boolean validarEn(TareaCompleja contenedor) {
        return contenedor.validarSpike(this);
    }
}
