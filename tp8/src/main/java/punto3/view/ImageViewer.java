package punto3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageViewer extends JFrame {

    public ImageViewer(BufferedImage image, String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel personalizado que escala la imagen
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    double panelWidth = getWidth();
                    double panelHeight = getHeight();
                    double imageWidth = image.getWidth();
                    double imageHeight = image.getHeight();

                    // Calcular ratio para "Fit Zoom"
                    double ratio = Math.min(panelWidth / imageWidth, panelHeight / imageHeight);

                    int newWidth = (int) (imageWidth * ratio);
                    int newHeight = (int) (imageHeight * ratio);

                    // Centrar la imagen
                    int x = (int) ((panelWidth - newWidth) / 2);
                    int y = (int) ((panelHeight - newHeight) / 2);

                    g.drawImage(image, x, y, newWidth, newHeight, this);
                }
            }
        };

        if (image != null) {
            imagePanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        } else {
            imagePanel.setPreferredSize(new Dimension(800, 600));
        }
        add(imagePanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void showImage() {
        setVisible(true);
    }
}
