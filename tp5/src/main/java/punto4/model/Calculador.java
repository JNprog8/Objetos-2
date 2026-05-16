package punto4.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public abstract class Calculador {
    private static final String ERROR_LOG_NULO = "El log no puede ser nulo";
    private static final String ERROR_MES_INGRESADO_INVALIDO = "El mes debe estar entre 1 y 12. Valor recibido: ";
    private static final String ERROR_PRECIO_INGRESADO_NEGATIVO = "El precio del producto no puede ser negativo";

    private LogTransaction log;
    private int mesEnPromocion;

    public Calculador(int mesEnPromocion, LogTransaction log) {
        validarMes(mesEnPromocion);
        this.log = Objects.requireNonNull(log, ERROR_LOG_NULO);
        this.mesEnPromocion = mesEnPromocion;
    }

    private static void validarMes(int mesEnPromocion) {
        if (mesEnPromocion < 1 || mesEnPromocion > 12) {
            throw new IllegalArgumentException(ERROR_MES_INGRESADO_INVALIDO + mesEnPromocion);
        }
    }

    private static void validarPrecio(double precioProducto) {
        if (precioProducto < 0) {
            throw new IllegalArgumentException(ERROR_PRECIO_INGRESADO_NEGATIVO);
        }
    }

    public double calcularPrecio(double precioProducto) {
        validarPrecio(precioProducto);
        double precioTotal = precioProducto + this.sobrecargo(precioProducto);
        log.log(this.getClass().getName());
        return precioTotal;
    }

    protected abstract double sobrecargo(double precioProducto);

    protected boolean esMesEnPromocion() {
        return Month.of(mesEnPromocion).equals(LocalDate.now().getMonth());
    }
}
