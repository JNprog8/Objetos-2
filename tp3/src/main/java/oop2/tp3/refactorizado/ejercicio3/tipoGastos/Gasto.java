package oop2.tp3.refactorizado.ejercicio3.tipoGastos;

public abstract class Gasto {
    private int monto;

    public Gasto(int monto) {
        this.monto = monto;
    }

    public int monto() {
        return this.monto;
    }

    public int montoComida(){
        return esComida() ? monto() : 0;
    }

    public abstract String nombre();
    public abstract boolean esComida();
    public abstract boolean tieneExceso();
}