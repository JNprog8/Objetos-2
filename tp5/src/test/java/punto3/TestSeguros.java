package punto3;

import org.junit.jupiter.api.Test;
import punto3.model.component.Seguro;
import punto3.model.composite.Paquete;
import punto3.model.leaf.Automovil;
import punto3.model.leaf.Hogar;
import punto3.model.leaf.Vida;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSeguros {

    @Test
    public void testSeguroIndividual() {
        Seguro hogar = new Hogar(100.0);
        assertEquals(100.0, hogar.calcularCosto());
    }

    @Test
    public void testPaqueteSimple() {
        Paquete paquete = new Paquete("Hogar y Vida");
        Seguro hogar = new Hogar(100.0);
        Seguro vida = new Vida(200.0);

        paquete.agregarSeguro(hogar);
        paquete.agregarSeguro(vida);

        // 2 items -> 10% descuento
        // Total = (100 + 200) * 0.90 = 270.0
        assertEquals(270.0, paquete.calcularCosto());
    }

    @Test
    public void testPaqueteCompuesto() {
        Paquete p1 = new Paquete("P1");
        p1.agregarSeguro(new Hogar(100.0));
        p1.agregarSeguro(new Vida(100.0));
        // p1 = 200 * 0.90 = 180.0

        Paquete p2 = new Paquete("P2");
        p2.agregarSeguro(p1);
        p2.agregarSeguro(new Automovil(200.0));
        // Total = (180.0 + 200.0) * 0.90 = 380.0 * 0.90 = 342.0

        assertEquals(342.0, p2.calcularCosto());
    }
}
