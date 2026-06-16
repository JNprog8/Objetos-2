package doubleDispatchTest.punto1;

import doubleDispatch.punto1.model.abstractElement.ElementoDeJuego;
import doubleDispatch.punto1.model.concreteElement.Papel;
import doubleDispatch.punto1.model.concreteElement.Piedra;
import doubleDispatch.punto1.model.concreteElement.Tijera;
import doubleDispatch.punto1.model.dispatchLogic.Jugada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JugadaTest {

    public static final ElementoDeJuego TIJERA = new Tijera();
    public static final ElementoDeJuego PIEDRA = new Piedra();
    public static final ElementoDeJuego PAPEL = new Papel();

    @Test
    void testPiedraLeGanaATijera() {
        String resultado = new Jugada().jugar(PIEDRA, TIJERA);
        assertEquals(Jugada.GANASTE, resultado);
    }

    @Test
    void testPiedraPierdeConPapel() {
        String resultado = new Jugada().jugar(PIEDRA, PAPEL);
        assertEquals(Jugada.PERDISTE, resultado);
    }

    @Test
    void testPiedraEmpataConPiedra() {
        String resultado = new Jugada().jugar(PIEDRA, PIEDRA);
        assertEquals(Jugada.EMPATE, resultado);
    }

    @Test
    void testPapelEmpataConPapel() {
        String resultado = new Jugada().jugar(PAPEL, PAPEL);
        assertEquals(Jugada.EMPATE, resultado);
    }

    @Test
    void testPapelPierdeConTijera() {
        String resultado = new Jugada().jugar(PAPEL, TIJERA);
        assertEquals(Jugada.PERDISTE, resultado);
    }

    @Test
    void testPapelLeGanaAPiedra() {
        String resultado = new Jugada().jugar(PAPEL, PIEDRA);
        assertEquals(Jugada.GANASTE, resultado);
    }

    @Test
    void testTijeraEmpataConTijera() {
        String resultado = new Jugada().jugar(TIJERA, TIJERA);
        assertEquals(Jugada.EMPATE, resultado);
    }

    @Test
    void testTijeraPierdeConPiedra() {
        String resultado = new Jugada().jugar(TIJERA, PIEDRA);
        assertEquals(Jugada.PERDISTE, resultado);
    }

    @Test
    void testTijeraLeGanaAPapel() {
        String resultado = new Jugada().jugar(TIJERA, PAPEL);
        assertEquals(Jugada.GANASTE, resultado);
    }
}
