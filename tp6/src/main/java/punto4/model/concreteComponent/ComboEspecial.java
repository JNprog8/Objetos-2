package punto4.model.concreteComponent;

import punto4.model.component.Combo;

public class ComboEspecial implements Combo {

    private static final String DESCRIPCION_ESPECIAL = "Combo Especial (Hamburguesa Doble + Papas Grandes + Postre)";
    private static final float PRECIO_ESPECIAL = 8000;

    @Override
    public String descripcion() {
        return DESCRIPCION_ESPECIAL;
    }

    @Override
    public float precio() {
        return PRECIO_ESPECIAL;
    }
}
