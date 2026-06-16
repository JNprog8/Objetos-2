package punto2.model.proxy;

import punto2.model.Usuario;
import punto2.model.authorization.PermisoAdmin;
import punto2.model.authorization.PermisoIntermedio;
import punto2.model.realsubject.FileAccess;
import punto2.model.subject.File;

import java.io.IOException;

public class FileAccessProxy implements File {
    private static final String I = "i";
    private static final String M = "m";
    private FileAccess fileAccess;
    private String content;
    private Usuario usuario;

    public FileAccessProxy(FileAccess fileAccess, Usuario usuario) {
        this.usuario = usuario;
        this.fileAccess = fileAccess;
        this.content = "";
    }

    @Override
    public String readFile() throws IOException {

        if (this.fileAccess.nombreComienzaCon(I)) {
            if (!usuario.poseePermiso(new PermisoAdmin())) {
                throw new RuntimeException("Permisos insuficientes");
            }

        }
        if (fileAccess.nombreComienzaCon(M)) {
            if (!usuario.poseePermiso(new PermisoAdmin()) && !usuario.poseePermiso(new PermisoIntermedio())) {
                throw new RuntimeException("Permisos insuficientes");
            }
        }
        if (content.equals("")) {
            content = fileAccess.readFile();
        }
        return content;
    }

    @Override
    public boolean nombreComienzaCon(String prefijo) {
        return fileAccess.nombreComienzaCon(prefijo);
    }
}
