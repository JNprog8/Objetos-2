package punto3.model.realsubject;

import punto3.model.subject.Image;
import punto3.view.ImageViewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFile implements Image {
    private BufferedImage image;
    private String path;

    public ImageFile(String path) {
        this.path = path;
        image = load(path);
    }

    @Override
    public void display() {
        var viewer = new ImageViewer(image, "Visualizando: " + path);
        viewer.showImage();
    }

    private BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
