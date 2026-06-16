package punto3.model.proxy;

import punto3.model.realsubject.ImageFile;
import punto3.model.subject.Image;

public class ImageProxy implements Image {
    private final String path;
    private ImageFile imageFile;
    private boolean isLoaded;

    public ImageProxy(String path) {
        this.path = path;
        this.isLoaded = false;
    }

    @Override
    public void display() {
        if (!isLoaded) {
            System.out.println("Cache MISS - Cargando desde disco: " + path);
            this.imageFile = new ImageFile(path);
            this.isLoaded = true;
        } else {
            System.out.println("Cache HIT - Imagen ya en memoria: " + path);
        }
        this.imageFile.display();
    }
}
