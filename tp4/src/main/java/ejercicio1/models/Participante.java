package ejercicio1.models;

public class Participante {
    public static final String REGION_CHINA = "China";
    public static final String REGION_US = "US";
    public static final String REGION_EUROPA = "Europa";

    private String nombre;
    private String telefono;
    private String region;

    public Participante(String nombre, String telefono, String region) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new RuntimeException("El teléfono no puede estar vacío.");
        }
        if (!validarTelefono(telefono)) {
            throw new RuntimeException("El formato del teléfono debe ser NNNN-NNNNNN.");
        }
        if (!esRegionValida(region)) {
            throw new RuntimeException("Región desconocida.");
        }
        this.nombre = nombre;
        this.telefono = telefono;
        this.region = region;
    }

    private boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    private boolean esRegionValida(String region) {
        return REGION_CHINA.equals(region) || REGION_US.equals(region) || REGION_EUROPA.equals(region);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getRegion() {
        return this.region;
    }
}
