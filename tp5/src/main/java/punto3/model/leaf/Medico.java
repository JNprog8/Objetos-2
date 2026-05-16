package punto3.model.leaf;

public class Medico extends SeguroAtomico {

    private static final String MEDICO = "Seguro Médico: $";

    public Medico(double precio) {
        super(precio);
    }

    @Override
    public String mostrar() {
        return MEDICO + calcularCosto();
    }
}
