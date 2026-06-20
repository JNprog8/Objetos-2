package punto1.model;

public class Puerta {
    private EstadoPuerta estadoPuerta;

    public Puerta() {
        this.estadoPuerta = new EstadoCerrada(this);
    }

    public String estado() {
        return estadoPuerta.toString();
    }

    public void abrir() {
        this.estadoPuerta.abrir();
    }

    public void cerrar() {
        this.estadoPuerta.cerrar();
    }

    protected void nuevoEstado(EstadoPuerta nuevoEstado) {
        this.estadoPuerta = nuevoEstado;
    }
}
