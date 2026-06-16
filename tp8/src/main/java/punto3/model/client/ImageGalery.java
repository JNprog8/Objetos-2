package punto3.model.client;

import punto3.model.proxy.ImageProxy;

/**
 * En el repo: https://github.com/enriquemolinari/oop2-patterns4 en el paquete unrn.ejercicio, se
 * encuentran las clases ImageFile (carga una imagen de disco a memoria y permite mostrarla en una
 * pantalla) e ImageGalery que invoca los servicios de ImageFile. Implemente utilizando el patron
 * proxy, un proxy que no vuelva a cargar la imagen desde el disco si ya fue cargada una vez. Realice
 * un diagrama de clases.
 */
public class ImageGalery {

    private static final String IMAGE_PATH = "src/main/java/punto3/image/animal.jpg";

    public static void main(String[] args) {
        var imagen = new ImageProxy(IMAGE_PATH);

        System.out.println("--- Primera llamada ---");
        imagen.display();

        System.out.println("\n--- Segunda llamada (debería usar cache) ---");
        imagen.display();
    }

}
