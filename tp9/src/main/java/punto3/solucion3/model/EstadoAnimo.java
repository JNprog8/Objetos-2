package punto3.solucion3.model;

//State: Define el comportamiento (deseo) basado en el animo.
//Cada estado sabe decir si es aplicable según la energía actual.
public interface EstadoAnimo {
    void realizarDeseo(Golondrina golondrina);

    boolean aplicarSegunEnegia(int energia);
}
