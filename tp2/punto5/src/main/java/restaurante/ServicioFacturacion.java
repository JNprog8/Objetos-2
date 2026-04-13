package restaurante;

import logica.facturacion.propina.Propina;
import logica.facturacion.tarjetas.TarjetaCredito;
import logica.mesa.Mesa;

public class ServicioFacturacion {

    public ServicioFacturacion() {
    }

    public void cerrarMesaYFacturar(Mesa mesa, TarjetaCredito tarjeta, Propina propina) {
        mesa.cerrarMesa(tarjeta, propina);
    }
}
