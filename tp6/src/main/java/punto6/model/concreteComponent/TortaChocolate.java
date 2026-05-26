package punto6.model.concreteComponent;

import punto6.model.component.Torta;

public class TortaChocolate implements Torta {
    private static final String TORTA_CHOCOLATE_DESCRIPCION = "Torta de bizcochuelo sabor chocolate";
    private static final float COSTO = 10;

    @Override
    public float precio() {
        return COSTO;
    }

    @Override
    public String descripcion() {
        return TORTA_CHOCOLATE_DESCRIPCION;
    }
}
