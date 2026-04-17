package dominio;

import interfaces.A;
import interfaces.B;
import interfaces.C;
import interfaces.D;

public class AprendiendoLambdas {
    public void unMetodo(A a) {
        a.metodo();
    }

    public void unMetodo(B b) {
        b.metodo("unString");
    }

    public void unMetodo(C c) {
        System.out.println(c.metodo("otroString") ? "true" : "false");
    }

    public void unMetodo(D<Long, Long> d) {
        d.metodo(10L);
    }
}