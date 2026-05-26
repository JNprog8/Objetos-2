package punto4.model.decorator;

import punto4.model.component.Combo;

public abstract class AdicionalDecorator implements Combo {
    protected Combo combo;

    protected AdicionalDecorator(Combo combo) {
        this.combo = combo;
    }

//    @Override
//    public abstract String descripcion();
//
//    @Override
//    public abstract float precio();
}
