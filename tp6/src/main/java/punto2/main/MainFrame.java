package punto2.main;

import punto2.model.target.*;

import javax.swing.*;

/**
 * Dado el ejemplo del Canvas, Figuras aplicando composite
 * (https://github.com/enriquemolinari/oop2-patterns1), quitemos la dependencia de cada Figura
 * sobre Graphics2D utilizando el patrón adapter. Primero cree una interfaz “Panel” que ofrezca
 * los servicios de pintar los tres tipos de figura, círculo, línea y texto. Luego implemente un
 * adapter para adaptar Panel a Graphics2D de modo que el sistema funcione. De esta forma
 * tenemos el mismo resultado pero habiendo quitado la dependencia de las figuras sobre
 * Graphics2D.
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        super("Dibujar Figuras");

        Canvas canvas = new Canvas();
        canvas.agregarFigura(new Linea(new Coordenada(50, 50), 200));
        canvas.agregarFigura(new Circulo(new Coordenada(200, 200), 50));
        canvas.agregarFigura(new Texto(new Coordenada(100, 300), "Hola Mundo"));
        canvas.agregarFigura(new Imagen(new Coordenada(255, 80), "src/main/java/punto2/images/image.jpg", 230, 240));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        getContentPane().add(canvas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
