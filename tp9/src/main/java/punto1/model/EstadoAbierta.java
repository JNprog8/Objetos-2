package punto1.model;

public class EstadoAbierta implements EstadoPuerta {
    private Puerta puerta;

    public EstadoAbierta(Puerta puerta) {
        this.puerta = puerta;
    }

    @Override
    public void abrir() {
        System.out.println("NO se puede abrir una puerta abierta");
    }

    @Override
    public void cerrar() {
        System.out.println("Abriendo la puerta...");
        puerta.nuevoEstado(new EstadoCerrada(puerta));
    }

    @Override
    public String toString() {
        return "ABIERTA";
    }
}
