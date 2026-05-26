package punto6.model.decorator;

import punto6.model.component.Torta;

public abstract class TortaDecorator implements Torta {
    private Torta torta;

    public TortaDecorator(Torta torta) {
        this.torta = torta;
    }

    @Override
    public float precio() {
        return torta.precio();
    }

    @Override
    public String descripcion() {
        return torta.descripcion();
    }
}
