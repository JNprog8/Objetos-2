package punto4.model.concreteComponent;

import punto4.model.component.Combo;

public class ComboFamiliar implements Combo {

    private static final float PRECIO_FAMILIAR = 10000;
    private static final String DESCRIPCION_FAMILIAR = "Combo Familiar (2 Hamburguesas + Papas + 2 Gaseosas)";

    @Override
    public String descripcion() {
        return DESCRIPCION_FAMILIAR;
    }

    @Override
    public float precio() {
        return PRECIO_FAMILIAR;
    }
}
