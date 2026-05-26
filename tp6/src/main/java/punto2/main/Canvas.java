package punto2.main;

import punto2.model.adapter.Graphics2DAdapter;
import punto2.model.target.Figura;
import punto2.model.target.Panel;

import javax.swing.*;
import java.awt.*;

class Canvas extends JPanel {
    private java.util.List<Figura> figuras;

    public Canvas() {
        figuras = new java.util.ArrayList<>();
    }

    public void agregarFigura(Figura figura) {
        figuras.add(figura);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Panel panel = new Graphics2DAdapter((Graphics2D) g);
        for (Figura figura : figuras) {
            figura.dibujar(panel);
        }
    }
}
