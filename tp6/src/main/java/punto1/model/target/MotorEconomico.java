package punto1.model.target;

public class MotorEconomico implements Motor {

    @Override
    public void arrancar() {
        System.out.println("[Motor Económico] Arrancando...");
    }

    @Override
    public void acelerar() {
        System.out.println("[Motor Económico] Acelerando...");
    }

    @Override
    public void apagar() {
        System.out.println("[Motor Económico] Apagando...");
    }
}
