package punto2.model.target;

public class Circulo implements Figura {
    private int radio;
    private Coordenada coordenada;

    public Circulo(Coordenada coordenada, int radio) {
        this.radio = radio;
        this.coordenada = coordenada;
    }

    @Override
    public void dibujar(Panel panel) {
        panel.dibujarCirculo(coordenada, radio);
    }
}
