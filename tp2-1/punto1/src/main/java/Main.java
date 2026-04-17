import dominio.AprendiendoLambdas;
import interfaces.C;

public class Main {
    public static void main(String[] args) {
        AprendiendoLambdas a = new AprendiendoLambdas();
        a.unMetodo((b) -> {
            System.out.println("abcd" + b);
        });
        a.unMetodo(() -> System.out.println("abcd"));
        a.unMetodo((variable) -> {
            System.out.println("abcd");
        });
        a.unMetodo((C) (variable) -> {
            System.out.println("abcd");
            return true;
        });
        a.unMetodo((Long variable) -> {
            System.out.println("abcd");
            return 10L;
        });
    }
}
