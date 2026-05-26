package punto1.model.target;

public class MotorComun implements Motor {

    @Override
    public void arrancar() {
        System.out.println("[Motor Común] Arrancando...");
    }

    @Override
    public void acelerar() {
        System.out.println("[Motor Común] Acelerando...");
    }

    @Override
    public void apagar() {
        System.out.println("[Motor Común] Apagando...");
    }
}
