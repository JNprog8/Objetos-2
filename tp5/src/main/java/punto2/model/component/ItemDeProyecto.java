package punto2.model.component;

import punto2.model.composite.ItemCompuesto;

import java.time.Duration;

public interface ItemDeProyecto {
    Duration calcularDuracion();

    String mostrar();

    void agregarA(ItemCompuesto contenedor);
}
