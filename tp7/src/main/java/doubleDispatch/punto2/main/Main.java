package doubleDispatch.punto2.main;

import doubleDispatch.punto2.model.LaserX;
import doubleDispatch.punto2.model.composite.Director;
import doubleDispatch.punto2.model.composite.Gerente;
import doubleDispatch.punto2.model.composite.LiderDeProyecto;
import doubleDispatch.punto2.model.composite.MandoMedio;
import doubleDispatch.punto2.model.leaf.EmpleadoRegular;

/**
 * El siguiente repositorio
 * https://github.com/enriquemolinari/oop2-ejercicios-doubledispatch en el paquete
 * laserx, tiene implementado el ejercicio del patrón composite sobre Directores,
 * Mandos Medios, etc. Implemente la validación sobre qué cargos pueden tener de
 * subordinados a que cargos utilizando double dispatch. Existen unos tests
 * automatizados comentados que deben correr en verde para comprobar que todo
 * funciona una vez terminada la implementación de la validación. Puede modificarlos
 * si es necesario.
 */
public class Main {
    public static void main(String[] args) {
        try {
            var director = new Director("Ana (Directora)", 5000);
            var gerente = new Gerente("Pedro (Gerente)", 3000);
            var mando = new MandoMedio("Luis (Mando Medio)", 2000);
            var lider = new LiderDeProyecto("Sonia (Líder)", 1500);
            var junior = new EmpleadoRegular("Tobi (Junior)", 1000);

            director.agregarSubordinado(gerente);
            gerente.agregarSubordinado(mando);
            mando.agregarSubordinado(lider);
            lider.agregarSubordinado(junior);

            var laserX = new LaserX(director);
            System.out.println("Sistema LaserX configurado correctamente.");
            System.out.println("Masa salarial total: " + laserX.masaSalarial());

        } catch (Exception e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}
