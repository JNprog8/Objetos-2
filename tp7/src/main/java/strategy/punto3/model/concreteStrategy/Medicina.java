package strategy.punto3.model.concreteStrategy;

import strategy.punto3.model.strategy.CalculadorDePrecios;

public class Medicina implements CalculadorDePrecios {
    @Override
    public double calcularPrecioTotal(double precioBase) {
        double impuestos = 0.0;
        double descuentos = 0.0;
        if (precioBase > 50) {
            descuentos = 0.1;
        }
        double total = precioBase * (1 + impuestos) * (1 - descuentos);
        if (precioBase > 100) {
            total -= 10;
        }
        return total;
    }
}
