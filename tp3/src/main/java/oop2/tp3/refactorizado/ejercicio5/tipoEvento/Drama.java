package oop2.tp3.refactorizado.ejercicio5.tipoEvento;

public class Drama extends Evento{
    private static final int MONTO_MINIMO = 40000;
    private static final int LIMITE_ESPECTADORES = 30;

    public Drama(String titulo){
        super(titulo);
    }

    @Override
    protected int limiteEspectadores() {
        return LIMITE_ESPECTADORES;
    }

    @Override
    public double calcularMonto(int espectadores) {
        double monto = MONTO_MINIMO;

        if (espectadores > limiteEspectadores()) {
            monto += 1000 * (espectadores - limiteEspectadores());
        }

        return monto;
    }
}
