package punto2.model.target;

import javax.swing.*;
import java.awt.*;

public class Imagen implements Figura {
    private Coordenada coordenada;
    private Image imagen;
    private int ancho;
    private int alto;

    public Imagen(Coordenada coordenada, String ruta, int ancho, int alto) {
        this.coordenada = coordenada;
        this.imagen = new ImageIcon(ruta).getImage();
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public void dibujar(Panel panel) {
        panel.dibujarImagen(coordenada, imagen, ancho, alto);
    }
}
