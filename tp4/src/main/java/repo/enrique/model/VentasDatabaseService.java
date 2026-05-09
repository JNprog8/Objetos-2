package repo.enrique.model;

import java.time.LocalDateTime;
import java.util.List;

public interface VentasDatabaseService {
    void nuevaVenta(LocalDateTime fechaDeVenta,
                    float montoTotal,
                    float cantidadLitros,
                    String tipo);

    List<VentaData> ventas();
}
