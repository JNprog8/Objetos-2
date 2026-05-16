package punto3.model.leaf;

import punto3.model.component.Seguro;

public abstract class SeguroAtomico implements Seguro {
    private double precio;

    public SeguroAtomico(double precio) {
        this.precio = precio;
    }

    @Override
    public double calcularCosto() {
        return this.precio;
    }

    @Override
    public abstract String mostrar();
}
