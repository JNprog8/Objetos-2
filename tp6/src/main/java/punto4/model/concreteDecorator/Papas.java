package punto4.model.concreteDecorator;

import punto4.model.component.Combo;
import punto4.model.decorator.AdicionalDecorator;

public class Papas extends AdicionalDecorator {
    private static final float PRECIO = 500;
    private static final String DESCRIPCION = ", con Papas";

    public Papas(Combo combo) {
        super(combo);
    }

    @Override
    public String descripcion() {
        return combo.descripcion() + DESCRIPCION;
    }

    @Override
    public float precio() {
        return combo.precio() + PRECIO;
    }
}
