package oop2.refactoring.automatizados.movemethod;

import java.util.ArrayList;
import java.util.List;

//1. Extract method
//2. Move method
//El "Move method" funciona si el destino es un parámetro o un variable de instancia
//Porque necesita cambiar el caller, incorporar la instancia a donde movio el método.
//Si descomento private Bla bla, tambien aparece como opción Bla como destino.
public class Filtro {
    //private Bla bla;

    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            // no aplica tell dont ask
            if (persona.nombre().startsWith("E")) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

}
