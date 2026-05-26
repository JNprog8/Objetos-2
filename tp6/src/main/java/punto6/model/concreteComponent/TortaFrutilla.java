package punto6.model.concreteComponent;

import punto6.model.component.Torta;

public class TortaFrutilla implements Torta {
    private static final float COSTO = 20;
    private static final String DESCRIPCION = "Torta de bizcochuelo sabor frutilla";

    @Override
    public float precio() {
        return COSTO;
    }

    @Override
    public String descripcion() {
        return DESCRIPCION;
    }
}
