package punto2.model.realsubject;

import punto2.model.subject.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccess implements File {
    private String ruta;
    private String nombreArchivo;

    public FileAccess(String ruta, String nombre) {
        this.ruta = ruta;
        this.nombreArchivo = nombre;
    }

    @Override
    public String readFile() throws IOException {
        return Files.readString(Paths.get(this.ruta + "/" + this.nombreArchivo));
    }

    @Override
    public boolean nombreComienzaCon(String prefijo) {
        return this.nombreArchivo.startsWith(prefijo);
    }
}
