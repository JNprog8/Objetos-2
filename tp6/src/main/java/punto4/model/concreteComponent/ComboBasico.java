package punto4.model.concreteComponent;

import punto4.model.component.Combo;

public class ComboBasico implements Combo {

    private static final float PRECIO_BASE = 5000;
    private static final String DESCRIPCION_BASE = "Combo Básico (Hamburguesa + Gaseosa)";

    @Override
    public String descripcion() {
        return DESCRIPCION_BASE;
    }

    @Override
    public float precio() {
        return PRECIO_BASE;
    }
}
