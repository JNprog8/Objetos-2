package punto3.model.leaf;

public class Hogar extends SeguroAtomico {

    private static final String HOGAR = "Seguro Hogar: $";

    public Hogar(double precio) {
        super(precio);
    }

    @Override
    public String mostrar() {
        return HOGAR + calcularCosto();
    }
}
