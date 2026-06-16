package strategy.punto3.model.concreteStrategy;

import strategy.punto3.model.strategy.CalculadorDePrecios;

public class Alimento implements CalculadorDePrecios {
    @Override
    public double calcularPrecioTotal(double precioBase) {
        double impuestos = 0.05;
        double descuentos = 0.0;
        if (precioBase > 100) {
            descuentos = 0.15;
        }
        double total = precioBase * (1 + impuestos) * (1 - descuentos);
        if (precioBase > 200) {
            total -= 10;
        }
        return total;
    }
}
