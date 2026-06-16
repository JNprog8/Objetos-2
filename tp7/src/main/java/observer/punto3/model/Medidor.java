package observer.punto3.model;

import observer.punto1.model.ClimaOnline;

public class Medidor implements Medible {
    private ClimaOnline clima;

    public Medidor(ClimaOnline clima) {
        this.clima = clima;
    }

    @Override
    public String medirTemperatura() {
        return this.clima.temperatura();
    }
}
