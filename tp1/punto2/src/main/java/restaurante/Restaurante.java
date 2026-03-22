package restaurante;

import mesa.Mesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Restaurante {

    private static final String ERROR_MESA_NO_ENCONTRADA = "No existe una mesa con el número: ";

    private String nombre;
    private List<Mesa> mesas;

    public Restaurante(String nombre) {
        this.nombre = nombre;
        this.mesas = new ArrayList<>();
    }

    public void agregarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public Mesa buscarMesa(int numero) {
        return this.mesas.stream()
                .filter(m -> m.tieneNumero(numero))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESA_NO_ENCONTRADA + numero));
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int cantidadMesas() {
        return this.mesas.size();
    }
}