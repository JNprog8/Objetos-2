package observer.punto4.model;

public class Participante {
    private final String nombre;
    private final String telefono;
    private final Region region;
    private final String email;

    public Participante(String nombre, String telefono, String region, String email) {
        this.nombre = validateNotBlank(nombre, "El nombre no puede estar vacío.");
        this.telefono = validatePattern(telefono, "\\d{4}-\\d{6}", "El formato del teléfono debe ser NNNN-NNNNNN.");
        this.region = Region.fromName(region);
        this.email = validatePattern(email, "^[A-Za-z0-9+_.-]+@(.+)$", "El email no es válido.");
    }

    private String validateNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new RuntimeException(message);
        }
        return value.trim();
    }

    private String validatePattern(String value, String regex, String message) {
        if (value == null || !value.matches(regex)) {
            throw new RuntimeException(message);
        }
        return value;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRegion() {
        return region.name();
    }

    public String getEmail() {
        return email;
    }

    public enum Region {
        CHINA("China"), US("US"), EUROPA("Europa");

        private final String label;

        Region(String label) {
            this.label = label;
        }

        public static Region fromName(String name) {
            for (Region r : values()) {
                if (r.label.equalsIgnoreCase(name)) return r;
            }
            throw new RuntimeException("Región desconocida: " + name);
        }
    }
}
