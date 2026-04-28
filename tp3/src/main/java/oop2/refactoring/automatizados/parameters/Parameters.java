package oop2.refactoring.automatizados.parameters;

//1. "Introduce Parameter": 0.1F a parametro descuento.
//2. "Inline Parameter": descuento vuelve atras
//3. "Change Signature" en cobrar, agregar String numertoTarjeta. Valor por defecto?
//4. "Introduce Parameter Object": price, quantity, y descuento en Compra

public class Parameters {
    public void cobrar() {
        double total = this.calculateTotal(100, 2);
        System.out.println("Total: " + total);
    }

    private double calculateTotal(double price, int quantity) {
        var total = price * quantity;
        return total + total * 0.1F;
    }
}

class Caller1 {
    public void caller1() {
        var p = new Parameters();
        p.cobrar();
    }
}

class Caller2 {
    public void caller2() {
        var p = new Parameters();
        p.cobrar();
    }
}
