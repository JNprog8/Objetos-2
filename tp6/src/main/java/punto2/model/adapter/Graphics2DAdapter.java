package punto2.model.adapter;

import punto2.model.target.Coordenada;
import punto2.model.target.Panel;

import javax.swing.*;
import java.awt.*;

public class Graphics2DAdapter implements Panel {
    private Graphics2D graphics2D;

    public Graphics2DAdapter(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    @Override
    public void dibujarCirculo(Coordenada coordenada, int radio) {
        graphics2D.drawOval(coordenada.x() - radio,
                coordenada.y() - radio,
                radio * 2,
                radio * 2);
    }

    @Override
    public void dibujarLinea(Coordenada coordenada, int longitud) {
        graphics2D.drawLine(coordenada.x(), coordenada.y(), coordenada.x() + longitud, coordenada.y());
    }

    @Override
    public void dibujarTexto(Coordenada coordenada, String texto) {
        graphics2D.drawString(texto, coordenada.x(), coordenada.y());
    }

    @Override
    public void dibujarImagen(Coordenada coordenada, Image imagen, int ancho, int alto) {
        graphics2D.drawImage(imagen, coordenada.x(), coordenada.y(), ancho, alto, null);
    }
}
