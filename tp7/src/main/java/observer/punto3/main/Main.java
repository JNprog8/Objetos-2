package observer.punto3.main;

import observer.punto1.model.ClimaOnline;
import observer.punto1.model.WeatherChannelService;
import observer.punto3.model.Medible;
import observer.punto3.model.Medidor;
import observer.punto3.model.concreteObserver.ConsoleObserver;
import observer.punto3.model.concreteObserver.FileObserver;
import observer.punto3.model.decorator.MedidorConObserver;

/**
 * Mejore la implementación anterior utilizando un Decorador. El resultado
 * final es que el ejercicio se resuelve utilizando ambos patrones: Observer y Decorador.
 */
public class Main {
    private static final String API_KEY = "TU_API_KEY";
    private static final String CIUDAD_PAIS = "Viedma,Argentina";

    public static void main(String[] args) {
        ClimaOnline canalClima = new WeatherChannelService(API_KEY, CIUDAD_PAIS);
        Medible medidorReal = new Medidor(canalClima);

        MedidorConObserver medidorConObserver = new MedidorConObserver(medidorReal);

        medidorConObserver.attach(new ConsoleObserver());
        medidorConObserver.attach(new FileObserver("temperaturas.txt", "src/main/java/observer/punto3"));

        System.out.println("Consultando clima...");
        medidorConObserver.medirTemperatura();
    }
}
