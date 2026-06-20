package punto3.solucion2.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class EstadoDeAnimo {

    public static EstadoDeAnimo toHandle(Golondrina golondrina) {
        Optional<EstadoDeAnimo> estado = estados().stream()
                .filter(estadoDeAnimo -> estadoDeAnimo.canHandle(golondrina))
                .findFirst();
        if (estado.isPresent()) return estado.get();
        throw new RuntimeException("No se encontró un Estado de animo valido");
    }

    private static List<EstadoDeAnimo> estados() {
        return Arrays.asList(
                new EstadoDeAnimoDebil(),
                new EstadoDeAnimoEuforico(),
                new EstadoDeAnimoNeutro()
        );
    }

    public abstract void realizarDeseoPara(Golondrina golondrina);

    public abstract boolean canHandle(Golondrina golondrina);
}
