package observer.punto5.model.propinas;

public class PropinaFija implements Propina {
    private final double porcentaje;

    private PropinaFija(int porcentajeEntero) {
        this.porcentaje = porcentajeEntero / 100.0;
    }

    public static PropinaFija dosPorciento() {
        return new PropinaFija(2);
    }

    public static PropinaFija tresPorciento() {
        return new PropinaFija(3);
    }

    public static PropinaFija cincoPorciento() {
        return new PropinaFija(5);
    }

    @Override
    public double calcularPropina(double montoBase) {
        return montoBase * porcentaje;
    }
}
