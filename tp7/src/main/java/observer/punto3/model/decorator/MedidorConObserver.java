package observer.punto3.model.decorator;

import observer.punto3.model.Medible;
import observer.punto3.model.subject.Subject;

/**
 * Decorador que añade capacidades de Sujeto Observado a cualquier objeto Medible.
 */
public class MedidorConObserver extends Subject implements Medible {
    private final Medible medible;

    public MedidorConObserver(Medible medible) {
        this.medible = medible;
    }

    @Override
    public String medirTemperatura() {
        String temperature = this.medible.medirTemperatura();
        this.notificar(temperature);
        return temperature;
    }
}
