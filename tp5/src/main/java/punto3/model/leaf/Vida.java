package punto3.model.leaf;

public class Vida extends SeguroAtomico {

    private static final String VIDA = "Seguro de Vida: $";

    public Vida(double precio) {
        super(precio);
    }

    @Override
    public String mostrar() {
        return VIDA + calcularCosto();
    }
}
