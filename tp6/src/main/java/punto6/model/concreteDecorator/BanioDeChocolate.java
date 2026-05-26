package punto6.model.concreteDecorator;

import punto6.model.component.Torta;
import punto6.model.decorator.TortaDecorator;

public class BanioDeChocolate extends TortaDecorator {

    private static final float COSTO_INCREMENTO = 0.15f;
    private static final String DESCRIPCION = " con baño de chocolate";

    public BanioDeChocolate(Torta torta) {
        super(torta);
    }

    @Override
    public float precio() {
        float precioBase = super.precio();
        return precioBase + (precioBase * COSTO_INCREMENTO);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + DESCRIPCION;
    }

    // Alternativa para el problema
//    private static final float COSTO_EXTRA = 1.15f;
//    @Override
//    public float precio() { return super.precio() * COSTO_EXTRA; }
}
