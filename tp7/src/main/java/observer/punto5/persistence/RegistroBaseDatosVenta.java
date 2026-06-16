package observer.punto5.persistence;

import observer.punto5.model.Observer;

public class RegistroBaseDatosVenta implements Observer {
    @Override
    public void update(double monto) {
        // Mocking database insertion
        System.out.println("Insertando en base de datos: Venta por monto " + monto);
    }
}
