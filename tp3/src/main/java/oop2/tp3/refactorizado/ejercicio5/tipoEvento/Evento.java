package oop2.tp3.refactorizado.ejercicio5.tipoEvento;

public abstract class Evento {
    private final String nombre;

    public Evento(String nombre) {
        this.nombre = nombre;
    }

    public String nombre(){
        return this.nombre;
    }

    protected abstract int limiteEspectadores();
    public abstract double calcularMonto(int cantidadEspectadores);

    public int calcularCreditos(int cantidadEspectadores) {
        return Math.max(limiteEspectadores() - 30, 0);
    }
}