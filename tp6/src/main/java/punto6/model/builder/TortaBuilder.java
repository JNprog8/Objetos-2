package punto6.model.builder;

import punto6.model.component.Torta;
import punto6.model.concreteComponent.TortaChocolate;
import punto6.model.concreteComponent.TortaFrutilla;
import punto6.model.concreteComponent.TortaVainilla;
import punto6.model.concreteDecorator.BanioDeChocolate;
import punto6.model.concreteDecorator.Rocklets;

public class TortaBuilder {
    private Torta torta;

    public TortaBuilder() {
    }

    private void validarBaseDeTorta() {
        if (this.torta != null) {
            throw new IllegalStateException("Ya se ha definido una base para esta torta");
        }
    }

    private void asegurarBase() {
        if (this.torta == null) {
            throw new IllegalStateException("Debe definir una base (Chocolate, Vainilla o Frutilla) antes de agregar adicionales");
        }
    }

    public TortaBuilder tortaBaseChocolate() {
        validarBaseDeTorta();
        this.torta = new TortaChocolate();
        return this;
    }

    public TortaBuilder tortaBaseVainilla() {
        validarBaseDeTorta();
        this.torta = new TortaVainilla();
        return this;
    }

    public TortaBuilder tortaBaseFrutilla() {
        validarBaseDeTorta();
        this.torta = new TortaFrutilla();
        return this;
    }

    public TortaBuilder conBanioChocolate() {
        asegurarBase();
        this.torta = new BanioDeChocolate(torta);
        return this;
    }

    public TortaBuilder conRocklets() {
        asegurarBase();
        this.torta = new Rocklets(torta);
        return this;
    }

    public Torta build() {
        asegurarBase();
        return this.torta;
    }
}
