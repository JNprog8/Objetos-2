package punto6.model.concreteComponent;

import punto6.model.component.Torta;

public class TortaVainilla implements Torta {
    private static final String TORTA_VAINILLA_DESCRIPCION = "Torta de bizcochuelo sabor vainilla";
    private static final float COSTO = 10;

    @Override
    public float precio() {
        return COSTO;
    }

    @Override
    public String descripcion() {
        return TORTA_VAINILLA_DESCRIPCION;
    }
}
