package observer.punto2.main;

import observer.punto1.model.ClimaOnline;
import observer.punto1.model.WeatherChannelService;
import observer.punto2.model.concreteObserver.ConsoleObserver;
import observer.punto2.model.concreteObserver.FileObserver;
import observer.punto2.model.concreteSubject.Medidor;

/**
 * 2. Utilizando el patrón Observer, escriba dos observadores:
 * a. Uno que guarde en un archivo de texto una entrada por cada lectura que se realiza
 * de la temperatura y la fecha en la que se realiza.
 * b. Otro que imprima en consola cada vez que se lea la temperatura. Si la temperatura
 * es menor a 12 grados, debe imprimir: “Hace frio, se encenderá la caldera”. Si la temperatura
 * es mayor a 17 grados, debe imprimir: “Hace calor, se encenderá el aire acondicionado”.
 */
public class Main {
    private static final String API_KEY = "TU_API_KEY";
    private static final String CIUDAD_PAIS = "Viedma,Argentina";

    public static void main(String[] args) {
        String apiKey = API_KEY;
        String ubicacion = CIUDAD_PAIS;

        ClimaOnline canalClima = new WeatherChannelService(apiKey, ubicacion);
        Medidor medidor = new Medidor(canalClima);

        medidor.attach(new ConsoleObserver());
        medidor.attach(new FileObserver("temperaturas.txt", "src/main/java/observer/punto2"));

        System.out.println("Consultando clima...");
        medidor.leerTemperatura();
    }
}
