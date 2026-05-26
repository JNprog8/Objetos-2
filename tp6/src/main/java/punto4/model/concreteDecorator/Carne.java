package punto4.model.concreteDecorator;

import punto4.model.component.Combo;
import punto4.model.decorator.AdicionalDecorator;

public class Carne extends AdicionalDecorator {
    private static final float PRECIO = 1000;
    private static final String DESCRIPCION = ", con Carne Extra";

    public Carne(Combo combo) {
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
