package punto3.model.composite;

import punto3.model.component.Seguro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Paquete implements Seguro {
    private static final double DESCUENTO_UNITARIO = 0.05;
    private static final String ERROR_NOMBRE_INVALIDO = "El nombre del paquete no puede estar vacío.";
    private static final String ERROR_SEGURO_NULO = "No se puede agregar un seguro nulo al paquete.";

    private static final String SEPARADOR = System.lineSeparator();
    private static final String IDENTACION = "  ";
    private static final String PREFIJO_ITEM = " - ";
    private static final String PAQUETE_LABEL = "Paquete: ";
    private static final String COSTO_TOTAL_LABEL = " (Costo Total: $";
    private static final String CIERRE_COSTO_LABEL = ")";

    private String nombre;
    private List<Seguro> seguros;

    public Paquete(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
        this.seguros = new ArrayList<>();
    }

    private static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(ERROR_NOMBRE_INVALIDO);
        }
    }

    public void agregarSeguro(Seguro seguro) {
        this.seguros.add(Objects.requireNonNull(seguro, ERROR_SEGURO_NULO));
    }

    @Override
    public String mostrar() {
        return seguros.stream()
                .map(seguro -> SEPARADOR + IDENTACION + PREFIJO_ITEM + seguro.mostrar().replace(SEPARADOR, SEPARADOR + IDENTACION))
                .collect(Collectors.joining("", PAQUETE_LABEL + nombre + COSTO_TOTAL_LABEL + calcularCosto() + CIERRE_COSTO_LABEL, ""));
    }

    @Override
    public double calcularCosto() {
        double subtotal = seguros.stream().mapToDouble(Seguro::calcularCosto).sum();
        double porcentajeDescuento = Math.min(DESCUENTO_UNITARIO * seguros.size(), 1.0);
        return subtotal * (1 - porcentajeDescuento);
    }

//    public List<Seguro> obtenerSeguros() {
//        return Collections.unmodifiableList(seguros);
//    }
//
//
//    public Optional<Seguro> buscarSeguroPorTexto(String texto) {
//        return seguros.stream()
//                .filter(s -> s.mostrar().contains(texto))
//                .findFirst();
//    }
}
