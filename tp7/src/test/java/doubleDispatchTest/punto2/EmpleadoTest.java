package doubleDispatchTest.punto2;

import doubleDispatch.punto2.model.LaserX;
import doubleDispatch.punto2.model.composite.*;
import doubleDispatch.punto2.model.leaf.EmpleadoRegular;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    // Estructura: Director -> Gerente -> MandoMedio -> LiderDeProyecto -> EmpleadoRegular
    @Test
    public void calculoSalarial() {
        var director = new Director("director", 1500);
        var gerente = new Gerente("gerente", 1000);
        var mandoMedio = new MandoMedio("mandoMedio", 500);
        var lider = new LiderDeProyecto("lider", 300);
        var empleadoRegular = new EmpleadoRegular("empleadoRegular", 100);

        director.agregarSubordinado(gerente);
        gerente.agregarSubordinado(mandoMedio);
        mandoMedio.agregarSubordinado(lider);
        lider.agregarSubordinado(empleadoRegular);

        var laserX = new LaserX(director);
        // 1500 + 1000 + 500 + 300 + 100 = 3400
        assertEquals(3400, laserX.masaSalarial());
    }


    @Test
    public void directorNoPuedeSerJefeDeEmpleadoRegular() {
        var director = new Director("director1", 1000);
        var empleadoRegular = new EmpleadoRegular("empleadoRegular", 100);

        var e = assertThrows(RuntimeException.class, () -> {
            director.agregarSubordinado(empleadoRegular);
        });

        assertTrue(e.getMessage().contains(EmpleadoJerarquico.ASIGNACION_EMPLEADO_INVALIDA));
    }

    @Test
    public void mandoMedioNoPuedeSerJefeDeDirector() {
        var director = new Director("director1", 1000);
        var mandoMedio = new MandoMedio("mandoMedio", 500);

        var e = assertThrows(RuntimeException.class, () -> {
            mandoMedio.agregarSubordinado(director);
        });

        assertTrue(e.getMessage().contains(EmpleadoJerarquico.ASIGNACION_EMPLEADO_INVALIDA));
    }

    @Test
    public void directorPuedeSerJefeDeGerente() {
        var director = new Director("director1", 1000);
        var gerente = new Gerente("gerente1", 800);
        director.agregarSubordinado(gerente);
        assertTrue(director.tieneDeEmpeadoA(gerente));
    }

    @Test
    public void mandoMedioPuedeSerJefeDeLiderDeProyecto() {
        var mandoMedio = new MandoMedio("mandoMedio", 500);
        var lider = new LiderDeProyecto("lider1", 300);
        mandoMedio.agregarSubordinado(lider);
        assertTrue(mandoMedio.tieneDeEmpeadoA(lider));
    }
}
