public class Main {
    public static void main(String[] args) {
        var al = new AprendiendoLambdas();

        // a. imprime true si el largo del String es par, false caso contrario
        al.unMetodo(s -> s.length() % 2 == 0);

        // b. imprime true si el String comienza con "a" minuscula, false caso contrario
        al.unMetodo(s -> s.startsWith("a"));
    }
}
