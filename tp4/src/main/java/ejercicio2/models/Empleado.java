package ejercicio2.models;

import java.time.LocalDate;

public class Empleado {
    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String mail;

    public Empleado(String apellido, String nombre, LocalDate fechaNacimiento, String mail) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.mail = mail;
    }

    public String apellido() {
        return apellido;
    }

    public String nombre() {
        return nombre;
    }

    public LocalDate fechaNacimiento() {
        return fechaNacimiento;
    }

    public String mail() {
        return mail;
    }

    public boolean cumpleAniosHoy(LocalDate hoy) {
        return fechaNacimiento.getMonth() == hoy.getMonth() &&
               fechaNacimiento.getDayOfMonth() == hoy.getDayOfMonth();
    }

    public void celebrarCumple(Notificador notificador, MensajeCumpleanios mensaje) {
        String texto = mensaje.generarPara(this);
        notificador.enviar(this.mail(), "Feliz Cumpleaños", texto);
    }
}
