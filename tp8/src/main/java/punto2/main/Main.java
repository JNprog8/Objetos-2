package punto2.main;

import punto2.model.Usuario;
import punto2.model.authorization.PermisoAdmin;
import punto2.model.authorization.PermisoBasico;
import punto2.model.authorization.PermisoIntermedio;
import punto2.model.proxy.FileAccessProxy;
import punto2.model.realsubject.FileAccess;

import java.io.IOException;
import java.util.List;


/**
 * La empresa Tres Estrellas desea modificar su sistema para agregar control de acceso a los
 * archivos que maneja.
 * Utilizando el patrón Proxy implemente el control de acceso a la lectura de los archivos. Aquellos
 * archivos cuyo nombre comienza con la letra “i” (de importante), solo los usuario con permiso
 * ADMIN pueden accederlos. Los archivos que comienzan con la letra “m”, lo pueden ver los
 * usuarios con permiso ADMIN e INTERMEDIO. Cualquier otro archivo, lo ven todos los usuarios
 * sin importar qué permiso tengan. Utilice Usuarios#possePermiso para verificar permisos. En caso
 * de intento de lectura sin permiso lance una excepción indicando el error.
 * Escriba un Main mostrando como se usa.
 * Realice el diagrama de clases, ponga claramente los metodo más importantes.
 */
public class Main {
    public static void main(String[] args) {
        // usuarios con permisos diferentes
        var admin = new Usuario("Ana", List.of(new PermisoAdmin()));
        var intermedio = new Usuario("Juan", List.of(new PermisoIntermedio()));
        var basico = new Usuario("Pedro", List.of(new PermisoBasico()));

        // archivos
        var archImportante = new FileAccess("src/main/java/punto2", "important.txt");
        var archMedio = new FileAccess("src/main/java/punto2", "medio.txt");
        var archPublico = new FileAccess("src/main/java/punto2", "publico.txt");

        System.out.println("--- Probando acceso a archivo importante ('i') ---");
        probarAcceso(archImportante, admin);      // permitir
        probarAcceso(archImportante, intermedio); // denegar

        System.out.println("\n--- Probando acceso a archivo intermedio ('m') ---");
        probarAcceso(archMedio, intermedio);      // permitir
        probarAcceso(archMedio, basico);          // denegar

        System.out.println("\n--- Probando acceso a archivo público ---");
        probarAcceso(archPublico, basico);         // permitir
    }

    private static void probarAcceso(FileAccess realFile, Usuario usuario) {
        var proxy = new FileAccessProxy(realFile, usuario);
        try {
            System.out.print("Usuario [" + usuario.poseePermiso(new PermisoAdmin()) + "] (Admin?) intentando leer " + realFile.nombreComienzaCon("i") + "... ");
            proxy.readFile();
            System.out.println("[ACCESO CONCEDIDO]");
        } catch (RuntimeException e) {
            System.out.println("ACCESO DENEGADO: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
