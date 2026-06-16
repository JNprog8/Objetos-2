package observer.punto5.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;

    protected Subject() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observador) {
        observers.add(observador);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notify(double monto) {
        for (Observer observador : observers) {
            observador.update(monto);
        }
    }
}
