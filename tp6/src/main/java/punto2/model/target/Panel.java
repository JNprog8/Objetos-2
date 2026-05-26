package punto2.model.target;

import java.awt.*;

public interface Panel {
    void dibujarCirculo(Coordenada coordenada, int radio);

    void dibujarLinea(Coordenada coordenada, int longitud);

    void dibujarTexto(Coordenada coordenada, String texto);

    void dibujarImagen(Coordenada coordenada, Image imagen, int ancho, int alto);
}
