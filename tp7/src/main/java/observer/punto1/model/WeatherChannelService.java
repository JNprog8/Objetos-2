package observer.punto1.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherChannelService implements ClimaOnline {
    private String apiKey;
    private String ciudad;
    private String urlApi;

    public WeatherChannelService(String apiKey, String ciudad) {
        this.apiKey = apiKey;
        this.ciudad = ciudad;
        this.urlApi = "https://api.openweathermap.org/data/2.5/weather?q="
                + this.ciudad + "&units=metric&appid=" + this.apiKey;
    }

    @Override
    public String temperatura() {
        try {
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder()
                    .uri(URI.create(this.urlApi))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                String temperature = extraerTemperaturaDeJson(jsonResponse);
                return temperature;
            } else {
                return "Error HTTP: " + response.statusCode();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Hubo un error al conectar con la API: " + e.getMessage());
        }
    }

    private String extraerTemperaturaDeJson(String json) {
        String claveABuscar = "\"temp\":";
        int indiceInicio = json.indexOf(claveABuscar);

        if (indiceInicio != -1) {
            indiceInicio += claveABuscar.length();
            int indiceFin = json.indexOf(",", indiceInicio);
            return json.substring(indiceInicio, indiceFin).trim();
        }
        return "Desconocida";
    }
}
