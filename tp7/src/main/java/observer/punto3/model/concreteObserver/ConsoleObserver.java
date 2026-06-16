package observer.punto3.model.concreteObserver;

import observer.punto3.model.observer.Observer;

public class ConsoleObserver implements Observer {
    private static final double LIMITE_FRIO = 12.0;
    private static final double LIMITE_CALOR = 17.0;

    @Override
    public void update(String temperature) {
        try {
            double temp = Double.parseDouble(temperature);
            System.out.println("Temperatura actual: " + temp + " °C");
            
            if (temp < LIMITE_FRIO) {
                System.out.println("Hace frio, se encenderá la caldera");
            }
            if (temp > LIMITE_CALOR) {
                System.out.println("Hace calor, se encenderá el aire acondicionado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Temperatura actual: " + temperature);
        }
    }
}
