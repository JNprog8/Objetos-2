package ejercicio2.models;

public class MensajeSimple implements MensajeCumpleanios {
    public String generarPara(Empleado empleado) {
        StringBuilder result = new StringBuilder("Feliz cumpleaños, ").append(empleado.nombre()).append(" ").append(empleado.apellido()).append("!").append(System.lineSeparator());
        return result.toString();
    }
}
