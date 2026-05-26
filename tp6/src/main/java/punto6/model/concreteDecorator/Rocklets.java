package punto6.model.concreteDecorator;

import punto6.model.component.Torta;
import punto6.model.decorator.TortaDecorator;

public class Rocklets extends TortaDecorator {

    private static final int COSTO_EXTRA = 5;
    private static final String DESCRIPCION = " con rocklets";

    public Rocklets(Torta torta) {
        super(torta);
    }

    public float precio() {
        return super.precio() + COSTO_EXTRA;
    }

    public String descripcion() {
        return super.descripcion() + DESCRIPCION;
    }
}
