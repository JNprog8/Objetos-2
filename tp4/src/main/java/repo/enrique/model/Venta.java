package repo.enrique.model;

import java.time.LocalDateTime;

public class Venta {
    private final Long idVenta;
    private final LocalDateTime fechaDeVenta;
    private final TipoDeCombustible tipo;
    private final float litrosCargados;
    private final float montoTotal;

    public Venta(Long idVenta, LocalDateTime fechaDeVenta, TipoDeCombustible tipo,
                 float litrosCargados,
                 float montoTotal) {

        this.idVenta = idVenta;
        this.fechaDeVenta = fechaDeVenta;
        this.tipo = tipo;
        this.litrosCargados = litrosCargados;
        this.montoTotal = montoTotal;
    }

    public String fechaVenta() {
        return new FormattedDayTime(fechaDeVenta).toString();
    }

    public String litrosCargados() {
        return String.valueOf(this.litrosCargados);
    }

    public String montoTotal() {
        return String.valueOf(this.montoTotal);
    }

    public String tipoCombustible() {
        return this.tipo.toString();
    }
}
