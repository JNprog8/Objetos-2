package resolucion.punto4;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:derby:memory:usuariosDb;create=true";

        // infraestructura
        EjecutadorOperaciones ejecutador = new EjecutadorOperaciones(jdbcUrl);

        // Inyectamos la infraestructura en componentes de datos
        UsuariosDDL usuariosDDL = new UsuariosDDL(ejecutador);
        Usuarios usuarios = new Usuarios(ejecutador);

        try {
            System.out.println("Iniciando operaciones de base de datos...");

            // 1. Crear estructura
            System.out.println("Creando tabla de usuarios...");
            usuariosDDL.crearTablaUsuarios();

            // 2. Insertar datos
            System.out.println("Insertando usuarios de prueba...");
            usuarios.insertar("Ana", "ana@mail.com");
            usuarios.insertar("Luis", "luis@mail.com");

            // 3. Actualizar datos
            System.out.println("Actualizando email de Ana...");
            // Nota: En Derby memory, el ID suele empezar en 1
            usuarios.actualizarEmail(1, "ana.actualizada@mail.com");

            System.out.println("¡Operaciones completadas con éxito!");

        } catch (RuntimeException e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}