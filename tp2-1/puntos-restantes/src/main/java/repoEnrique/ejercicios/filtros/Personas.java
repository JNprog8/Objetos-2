package oop2Enrique.ejercicios.filtros;

import java.util.ArrayList;
import java.util.List;

public class Personas {

    private static final String LETRA = "E";

    //    filtra la lista de personas devolviendo otra lista con
//    solo aquellas cuyo nombre comienza con E/
//    Sin Refactor
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().startsWith("E")) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    //    filtra la lista de personas devolviendo otra lista con
//    solo aquellas cuya cantidad de letras en el nombre sea par.
//    Sin Refactor
    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (persona.nombre().length() % 2 == 0) {
                resultado.add(persona);
            }
        }
        return resultado;
    }
}
