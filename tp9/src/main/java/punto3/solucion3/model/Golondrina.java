package punto3.solucion3.model;

import java.util.Arrays;
import java.util.List;

// sin cambiar en transiciones, delega LN a colaboradores
public class Golondrina {
    private final Metabolismo metabolismo;
    private final List<EstadoAnimo> estados;
    private int energia;

    public Golondrina(Metabolismo metabolismo, List<EstadoAnimo> estados) {
        this.energia = 45; // inicial al nacer
        this.metabolismo = metabolismo;
        this.estados = estados;
    }

    // static - estandar para crear una golondrina normal
    public static Golondrina crearEstandar() {
        return new Golondrina(
                new MetabolismoEstandar(),
                Arrays.asList(new EstadoDebil(), new EstadoEuforico(), new EstadoNormal())
        );
    }

    public void comer(int gramos) {
        this.energia += metabolismo.beneficioComer(gramos);
    }

    public void volar(int kilometros) {
        this.energia -= metabolismo.gastoVolar(kilometros);
    }

    public void realizarDeseo() {
        buscarEstadoActual().realizarDeseo(this);
    }

    public int energia() {
        return this.energia;
    }

    private EstadoAnimo buscarEstadoActual() {
        return estados.stream()
                .filter(estado -> estado.aplicarSegunEnegia(this.energia))
                .findFirst()
                .orElse(new EstadoNormal());
    }
}
