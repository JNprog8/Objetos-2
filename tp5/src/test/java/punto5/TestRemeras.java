package punto5;

import org.junit.jupiter.api.Test;
import punto5.model.Importadas;
import punto5.model.Nacional;
import punto5.model.Remera;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRemeras {

    @Test
    public void testRemeraImportada() {
        Remera importada = new Importadas(100.0f);
        // Precio Unitario: 100
        // Recargo (3%): 3
        // Impuesto Aduanero (5%): 5
        // Subtotal (con recargos/impuestos): 108
        // Aplicación del 25% del comercio sobre 108: 108 * 1.25 = 135
        assertEquals(135.0f, importada.calcularPrecio(), 0.001f);
    }

    @Test
    public void testRemeraNacional() {
        Remera nacional = new Nacional(100.0f);
        // Precio Unitario: 100
        // Recargo transporte (1.5%): 1.5
        // Bonificación (20%): 20
        // Subtotal: 100 + 1.5 - 20 = 81.5
        // Aplicación del 15% del comercio sobre 81.5: 81.5 * 1.15 = 93.725
        assertEquals(93.725f, nacional.calcularPrecio(), 0.001f);
    }
}
