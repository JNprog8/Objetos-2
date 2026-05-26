package punto4.model.builder;

import punto4.model.component.Combo;
import punto4.model.concreteComponent.ComboBasico;
import punto4.model.concreteComponent.ComboEspecial;
import punto4.model.concreteComponent.ComboFamiliar;
import punto4.model.concreteDecorator.Carne;
import punto4.model.concreteDecorator.Papas;
import punto4.model.concreteDecorator.Queso;
import punto4.model.concreteDecorator.Tomate;

public class PedidoBuilder {
    private Combo combo;

    public PedidoBuilder() {
    }

    private void validarBase() {
        if (this.combo != null) {
            throw new IllegalStateException("Ya se ha definido una base para este combo");
        }
    }

    private void asegurarBase() {
        if (this.combo == null) {
            throw new IllegalStateException("Debe definir una base (Basico, Especial o Familiar) antes de agregar adicionales");
        }
    }

    public PedidoBuilder comboBasico() {
        validarBase();
        this.combo = new ComboBasico();
        return this;
    }

    public PedidoBuilder comboEspecial() {
        validarBase();
        this.combo = new ComboEspecial();
        return this;
    }

    public PedidoBuilder comboFamiliar() {
        validarBase();
        this.combo = new ComboFamiliar();
        return this;
    }

    public PedidoBuilder conTomate() {
        asegurarBase();
        this.combo = new Tomate(this.combo);
        return this;
    }

    public PedidoBuilder conPapas() {
        asegurarBase();
        this.combo = new Papas(this.combo);
        return this;
    }

    public PedidoBuilder conCarne() {
        asegurarBase();
        this.combo = new Carne(this.combo);
        return this;
    }

    public PedidoBuilder conQueso() {
        asegurarBase();
        this.combo = new Queso(this.combo);
        return this;
    }

    public Combo build() {
        asegurarBase();
        return combo;
    }
}
