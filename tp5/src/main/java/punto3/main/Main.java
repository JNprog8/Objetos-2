package punto3.main;

import punto3.model.component.Seguro;
import punto3.model.composite.Paquete;
import punto3.model.leaf.*;

/**
 * 3. Se desea desarrollar una aplicación de gestión de seguros que pueda manejar seguros
 * de distinto tipo (hogar, automóvil, vida y médicos) y que permita crear paquetes de
 * seguros que agrupen seguros (y también otros paquetes). El costo de cada paquete está
 * dado por el costo individual de cada seguro, pero aplica un descuento del 5%,
 * acumulativo, por cada seguro incluido en el paquete.
 * a) Aplicando el patrón Composite, diseñe el diagrama de clases que permita modelar este
 * escenario y que permita calcular el costo de cada seguro y de los paquetes de seguros
 * ofrecidos por la compañía.
 * b) Implemente la solución en Java y dos casos de prueba.
 */

public class Main {
    public static void main(String[] args) {
        Seguro hogar = new Hogar(5000);
        Seguro auto = new Automovil(3000);
        Seguro vida = new Vida(2000);

        Paquete comboBasico = new Paquete("Combo Básico");
        comboBasico.agregarSeguro(hogar);
        comboBasico.agregarSeguro(auto);

        System.out.println("--- Combo Básico ---");
        System.out.println(comboBasico.mostrar());
        System.out.println("Costo esperado: (5000+3000) * 0.90 = 7200");

        Paquete comboPremium = new Paquete("Combo Premium");
        comboPremium.agregarSeguro(comboBasico);
        comboPremium.agregarSeguro(vida);
        comboPremium.agregarSeguro(new Medico(4000));

        System.out.println("\n--- Combo Premium ---");
        System.out.println(comboPremium.mostrar());
        System.out.println("Costo esperado: (7200+2000+4000) * 0.85 = 11220");
    }

}
