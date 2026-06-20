package ejercicio2.model;

public class Inscripto {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Concurso concurso;

    public Inscripto(String dni, String nombre, String apellido, String telefono, String email, Concurso concurso) {
        this.dni = validarNoVacio(dni, "El dni no puede ser vacio");
        this.nombre = validarNoVacio(nombre, "El nombre no puede ser vacio");
        this.apellido = validarNoVacio(apellido, "El apellido no puede ser vacio");
        this.telefono = validarTelefono(telefono);
        this.email = validarEmail(email);
        validarConcurso(concurso);
        this.concurso = concurso;
    }

    private static void validarConcurso(Concurso concurso) {
        if (concurso == null) {
            throw new IllegalArgumentException("Debe elegir un concurso");
        }
    }

    public String nombre() {
        return nombre;
    }

    public String apellido() {
        return apellido;
    }

    public String telefono() {
        return telefono;
    }

    public String email() {
        return email;
    }

    public int idConcurso() {
        return concurso.id();
    }

    private String validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    private String validarEmail(String email) {
        String valor = validarNoVacio(email, "El email no puede ser vacio");
        if (!checkEmail(valor)) {
            throw new IllegalArgumentException("El email debe ser valido");
        }
        return valor;
    }

    private String validarTelefono(String telefono) {
        String valor = validarNoVacio(telefono, "El telefono no puede ser vacio");
        if (!checkPhone(valor)) {
            throw new IllegalArgumentException("El telefono debe ingresarse como NNNN-NNNNNN");
        }
        return valor;
    }

    private boolean checkEmail(String email) {
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }

    private boolean checkPhone(String telefono) {
        return telefono.matches("\\d{4}-\\d{6}");
    }
}
