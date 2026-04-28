package resolucion.punto3;

public class Persona {
    private String nombre;
    private String apellido;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // La persona sabe si su nombre empieza con algo
    public boolean nombreEmpiezaCon(String letra) {
        return this.nombre.startsWith(letra);
    }

    // La persona sabe si su nombre tiene longitud par
    public boolean tieneNombrePar() {
        return this.nombre.length() % 2 == 0;
    }

    public String nombre() {
        return nombre;
    }

    public String apellido() {
        return apellido;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", apellido=" + apellido + "]";
    }
}
