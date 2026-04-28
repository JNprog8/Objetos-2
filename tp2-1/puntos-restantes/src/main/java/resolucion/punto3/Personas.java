package resolucion.punto3;

import java.util.ArrayList;
import java.util.List;

public class Personas {

    private static void filtrarCondicion(Filtrar filtro, Persona p, List<Persona> resultado) {
        if (filtro.evaluar(p)) resultado.add(p);
    }

    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        return filtrar(p, persona -> persona.nombreEmpiezaCon("E"));
    }

    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        return filtrar(p, Persona::tieneNombrePar);
    }

    private List<Persona> filtrar(List<Persona> lista, Filtrar filtro) {
        List<Persona> resultado = new ArrayList<>();
        lista.forEach(p -> {
            filtrarCondicion(filtro, p, resultado);
        });
        return resultado;
    }

    /* Metodos Antiguos

    //filtra la lista de personas devolviendo otra lista con
    //solo aquellas cuyo nombre comienza con E
    //usa streams, no lo pedia el enunciado
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        return p.stream().filter(persona -> persona.nombre().startsWith(LETRA)).toList();
    }

    //filtra la lista de personas devolviendo otra lista con
    //solo aquellas cuya cantidad de letras en el nombre sea par.
    //usa streams
    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        return p.stream().filter(persona -> persona.nombre().length() % 2 == 0).toList();
    }
     */

}
