package punto1.main;

import punto1.model.*;

public class Main {

    /**
     * La empresa “Laser X” posee los siguientes roles de empleados: directores, gerentes,
     * mandos medios, líderes de proyecto y empleados regulares. Todos tienen gente a su
     * cargo, salvo los empleados regulares. Los directores tienen a su cargo gerentes, los
     * gerentes a mandos medios, los mandos medios a líderes de proyecto y éstos a
     * empleados regulares.
     * a) Implemente en Java el modelo de objetos para permitir calcular el monto total salarial
     * de la empresa utilizando el patrón Composite. Escriba dos casos de test.
     */

    public static void main(String[] args) {

        var director = new Director("Carlos", 500000);
        var gerente = new Gerente("Ana", 300000);
        var mando = new MandoMedio("Pedro", 200000);
        var lider = new LiderDeProyecto("Lucia", 150000);
        var emp1 = new EmpleadoRegular("Juan", 100000);
        var emp2 = new EmpleadoRegular("Maria", 120000);

        lider.asignarSubordinado(emp1);
        lider.asignarSubordinado(emp2);

        mando.asignarSubordinado(lider);

        gerente.asignarSubordinado(mando);

        director.asignarSubordinado(gerente);

        Empresa laserX = new Empresa("Laser X");
        laserX.agregarEmpleadoPrincipal(director);

        var total = laserX.montoTotalSalarial();

        System.out.println("Total salarial de Laser X: " + total);
    }
}
