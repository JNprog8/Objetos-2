package observer.punto2.model.concreteSubject;

import observer.punto1.model.ClimaOnline;
import observer.punto2.model.subject.Subject;

public class Medidor extends Subject {
    private ClimaOnline clima;
    private String temperatura;

    public Medidor(ClimaOnline clima) {
        this.clima = clima;
    }

    public String leerTemperatura() {
        this.temperatura = this.clima.temperatura();
        this.notify(this.temperatura);
        return this.temperatura;
    }
}
