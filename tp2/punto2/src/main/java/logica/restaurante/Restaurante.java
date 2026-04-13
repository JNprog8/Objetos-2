package logica.restaurante;

import logica.facturacion.RegistrarFactura;
import logica.mesa.Mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Restaurante {
    private String nombre;
    private List<Mesa> mesas;

    public Restaurante(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.mesas = new ArrayList<>();
    }

    private static void validarRegistros(List<RegistrarFactura> registros) {
        if (registros == null) {
            throw new IllegalArgumentException("La lista de registros no puede ser nula");
        }
    }

    public void agregarMesa(Mesa mesa, List<RegistrarFactura> registros) {
        validarMesa(mesa);
        validarRegistros(registros);

        registros.forEach(mesa::agregarRegistroFactura);
        this.mesas.add(mesa);
    }

    private void validarMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula");
        }
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo o vacío");
        }
    }

    public Optional<Mesa> buscarMesa(int numero) {
        return this.mesas.stream()
                .filter(mesa -> mesa.tieneNumero(numero))
                .findFirst();
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int cantidadMesas() {
        return this.mesas.size();
    }
}