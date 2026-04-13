package restaurante;

import logica.facturacion.RegistrarFactura;

import java.util.List;

public class Factura implements RegistrarFactura {
    private final List<RegistrarFactura> registros;

    public Factura(List<RegistrarFactura> registros) {
        validarFacturacion(registros);
        this.registros = registros;
    }

    private static void validarFacturacion(List<RegistrarFactura> registradores) {
        if (registradores == null || registradores.isEmpty()) {
            throw new IllegalArgumentException("La lista de registradores no puede ser nula o vacía.");
        }
    }

    @Override
    public void registrar(double monto) {
        registros.forEach(r -> r.registrar(monto));
    }
}
