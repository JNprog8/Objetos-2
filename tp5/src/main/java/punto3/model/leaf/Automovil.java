package punto3.model.leaf;

public class Automovil extends SeguroAtomico {

    private static final String AUTOMOVIL = "Seguro Automóvil: $";

    public Automovil(double precio) {
        super(precio);
    }

    @Override
    public String mostrar() {
        return AUTOMOVIL + calcularCosto();
    }
}
