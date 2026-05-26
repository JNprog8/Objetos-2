package punto6.model;

import punto6.model.component.Torta;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private final List<Torta> tortas = new ArrayList<>();

    public void agregarTorta(Torta torta) {
        tortas.add(torta);
    }

    public void imprimirOrden() {
        for (Torta torta : tortas) {
            System.out.printf(" %.2f %s\n", torta.precio(), torta.descripcion());
        }
    }
}

