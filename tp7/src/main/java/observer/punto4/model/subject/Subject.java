package observer.punto4.model.subject;

import observer.punto4.model.Participante;
import observer.punto4.model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;

    protected Subject() {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    public void notify(Participante participante) {
        for (Observer observer : observers) {
            observer.update(participante);
        }
    }
}
