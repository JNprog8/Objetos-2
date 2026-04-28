package oop2.refactoring.automatizados.extractmethod;

//Tengo código duplicado? lineas 8-11 y 17-20.
//1. Uso "Extract method" y el IDE me sugiere parametrizar la diferencia.
// Si hubiera hecho a mano el movimiento podria haber metido un bug.
public class ExtractMethodPlusParameter {
    public void methodOne() {
        System.out.println("más lógica methodOne");

        String[] words = {"Hello", "World", "Java"};
        for (String word : words) {
            System.out.println("Message: " + word);
        }
    }

    public void methodTwo() {
        System.out.println("más lógica methodTwo");

        String[] words = {"Hello", "World", "Java"};
        for (String w : words) {
            System.out.println("Message:" + w); // Nota el doble espacio después de "Message:"
        }
    }

}
