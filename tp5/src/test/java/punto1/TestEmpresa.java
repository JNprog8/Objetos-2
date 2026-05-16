package punto1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import punto1.model.Empresa;
import punto1.model.composite.Director;
import punto1.model.composite.Gerente;
import punto1.model.composite.LiderDeProyecto;
import punto1.model.composite.MandoMedio;
import punto1.model.leaf.EmpleadoRegular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmpresa {

    private Director director;
    private Gerente gerente;
    private MandoMedio mando;
    private LiderDeProyecto lider;
    private EmpleadoRegular emp1;
    private EmpleadoRegular emp2;

    @BeforeEach
    public void setUp() {
        director = new Director("Carlos", 100);
        gerente = new Gerente("Ana", 100);
        mando = new MandoMedio("Pedro", 100);
        lider = new LiderDeProyecto("Lucia", 100);
        emp1 = new EmpleadoRegular("Juan", 100);
        emp2 = new EmpleadoRegular("Maria", 100);
    }

    @Test
    public void verificarTotal() {
        Empresa laserX = new Empresa("Laser X");

        lider.asignarSubordinado(emp1);
        lider.asignarSubordinado(emp2);
        mando.asignarSubordinado(lider);
        gerente.asignarSubordinado(mando);
        director.asignarSubordinado(gerente);

        laserX.agregarEmpleado(director);

        var total = laserX.calcularTotalSalarial();
        assertEquals(600.0, total);
    }

    @Test
    public void verificarJerarquiaInvalida() {
        assertThrows(RuntimeException.class, () -> {
            lider.asignarSubordinado(gerente);
        });
    }
}
