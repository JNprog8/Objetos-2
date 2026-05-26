package punto4.model.concreteDecorator;

import punto4.model.component.Combo;
import punto4.model.decorator.AdicionalDecorator;

public class Tomate extends AdicionalDecorator {
    private static final float PRECIO = 200;
    private static final String DESCRIPCION = ", con Tomate";

    public Tomate(Combo combo) {
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
