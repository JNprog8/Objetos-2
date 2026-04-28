package oop2.tp3.refactorizado.ejercicio3.tipoGastos;

public class AlquilerAuto extends Gasto {

    public AlquilerAuto(int monto) {
        super(monto);
    }

    @Override
    public String nombre() {
        return "Alquiler de Autos";
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean tieneExceso() {
        return false;
    }
}
