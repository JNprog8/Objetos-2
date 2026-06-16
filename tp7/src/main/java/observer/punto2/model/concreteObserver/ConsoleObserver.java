package observer.punto2.model.concreteObserver;

import observer.punto2.model.observer.Observer;

public class ConsoleObserver implements Observer {

    @Override
    public void update(String temperature) {
        try {
            double temp = Double.parseDouble(temperature);
            System.out.println("Temperatura actual: " + temp + " °C");
            if (temp < 12) {
                System.out.println("Hace frio, se encenderá la caldera");
            }
            if (temp > 17) {
                System.out.println("Hace calor, se encenderá el aire acondicionado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Temperatura actual: " + temperature);
        }
    }
}
