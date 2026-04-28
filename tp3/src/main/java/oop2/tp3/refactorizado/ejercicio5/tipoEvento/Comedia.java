package oop2.tp3.refactorizado.ejercicio5.tipoEvento;

public class Comedia extends Evento{

    private static final int MONTO_MINIMO = 30000;
    private static final int LIMITE_ESPECTADORES = 20;

    public Comedia(String titulo){
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
            monto += 10000 + 500 * (espectadores - limiteEspectadores());
        }

        monto += 300 * espectadores;

        return monto;
    }

    @Override
    public int calcularCreditos(int espectadores) {
        return super.calcularCreditos(espectadores) + (int) Math.floor((double) espectadores / 5);
    }
}
