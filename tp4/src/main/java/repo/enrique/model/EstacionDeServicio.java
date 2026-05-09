package repo.enrique.model;

import java.util.List;

public interface EstacionDeServicio {
    float nuevaVenta(TipoDeCombustible tipoConbustible, float litrosCargados);

    List<Venta> listarVentas();
}
