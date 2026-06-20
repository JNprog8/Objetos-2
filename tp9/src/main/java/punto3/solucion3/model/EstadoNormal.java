package punto3.solucion3.model;

public class EstadoNormal implements EstadoAnimo {
    @Override
    public void realizarDeseo(Golondrina golondrina) {
        // no tiene deseo
    }

    @Override
    public boolean aplicarSegunEnegia(int energia) {
        // estado por defecto segun energia
        return energia >= 50 && energia <= 500;
    }
}
