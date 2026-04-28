package oop2.tp3.refactorizado.ejercicio3.tipoGastos;

public class Desayuno extends Gasto {

    private static final String DESAYUNO = "Desayuno";
    private static final int MONTO_EXCESO = 1000;

    public Desayuno(int monto) {
        super(monto);
    }

    @Override
    public String nombre() {
        return DESAYUNO;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean tieneExceso() {
        return monto() > MONTO_EXCESO;
    }
}