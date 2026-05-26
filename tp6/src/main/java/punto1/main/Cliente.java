package punto1.main;

import punto1.model.target.Motor;

public class Cliente {

    public void probarMotor(Motor motor) {

        System.out.println("--------------------------------");

        motor.arrancar();
        motor.acelerar();
        motor.apagar();

        System.out.println("--------------------------------");
    }
}
