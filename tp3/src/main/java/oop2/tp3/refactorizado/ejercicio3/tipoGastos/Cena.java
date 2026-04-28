package oop2.tp3.refactorizado.ejercicio3.tipoGastos;

public class Cena extends Gasto {
    private static final String CENA = "Cena";
    private static final int MONTO_EXCESO = 5000;

    public Cena(int monto) {
        super(monto);
    }

    @Override
    public String nombre() {
        return CENA;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean tieneExceso() {
        return this.monto() > MONTO_EXCESO;
    }
}
