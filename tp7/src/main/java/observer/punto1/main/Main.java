package observer.punto1.main;

import observer.punto1.model.ClimaOnline;
import observer.punto1.model.WeatherChannelService;

/**
 * Dado el siguiente Medidor de Temperatura que consulta un servicio web de Wheather Channel:
 * Se pide:
 * 1. Modifique la clase WheatherChannel para consumir el servicio web de
 * https://openweathermap.org/current.
 * Obtenga una cuenta gratuita desde: https://home.openweathermap.org/users/sign_up
 * (registrándose). Una vez registrado, recibirá un email con una API KEY que necesitan para
 * consumir los servicios. La activación de la API KEY demora unos 15 minutos una vez
 * creada.
 * Ejemplo de consumo del clima en Viedma en celsius:
 * https://api.openweathermap.org/data/2.5/weather?q=Viedma,Argentina&units=metric&APPID
 * =TU_API_KEY
 */

public class Main {

    private static final String API_KEY = "TU_API_KEY";
    private static final String CIUDAD_PAIS = "Viedma,Argentina";

    public static void main(String[] args) {
        String apiKey = API_KEY;
        String ubicacion = CIUDAD_PAIS;

        ClimaOnline canalClima = new WeatherChannelService(apiKey, ubicacion);

        System.out.println("Consultando clima...");
        System.out.println("Resultado: " + canalClima.temperatura() + " °C");
    }
}
