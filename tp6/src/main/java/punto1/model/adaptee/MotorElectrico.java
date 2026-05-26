package punto1.model.adaptee;

public class MotorElectrico {

    public void conectar() {
        System.out.println("[Motor Eléctrico] Conectando...");
    }

    public void activar() {
        System.out.println("[Motor Eléctrico] Activando...");
    }

    public void moverMasRapido() {
        System.out.println("[Motor Eléctrico] Moviendo más Rápido...");
    }

    public void detener() {
        System.out.println("[Motor Eléctrico] Deteniendo...");
    }

    public void desconectar() {
        System.out.println("[Motor Eléctrico] Desconectando...");
    }
}
