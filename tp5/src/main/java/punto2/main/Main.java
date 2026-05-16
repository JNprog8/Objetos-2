package punto2.main;

import punto2.model.Proyecto;
import punto2.model.composite.HistoriaDeUsuario;
import punto2.model.composite.Spike;
import punto2.model.leaf.Tarea;

import java.time.Duration;

/**
 * 2. Un Proyecto en Scrum está compuesto de muchos ítems de trabajo. Un ítem del
 * proyecto puede ser una historia de usuario, y las historias se componen de tareas.
 * También tenemos ítems de tipo spike (tareas de análisis).
 * a) Aplicando el patrón Composite diseñe el diagrama de clases que permita modelar este
 * escenario y que permita calcular el tiempo necesario para completar un proyecto o
 * alguna parte del mismo.
 * b) Implemente la solución en Java y escriba dos casos de test.
 */
public class Main {
    public static void main(String[] args) {

        var proyectoScrum = new Proyecto("Sistema de Gestión de Biblioteca");

        // 1. Historia de Usuario con dos Tareas y un Spike
        var t1 = new Tarea("Diseño de DB", "Crear tablas de usuarios", Duration.ofHours(4));
        var t2 = new Tarea("Implementar Login", "Lógica de backend", Duration.ofHours(8));
        var spike1 = new Spike("Spike: Investigación OAuth2", "Evaluar proveedores", Duration.ofHours(6));
        spike1.agregarItem(new Tarea("Lectura de Doc", "Documentación oficial", Duration.ofHours(2)));

        var hu1 = new HistoriaDeUsuario("HU1: Autenticación de usuarios");
        hu1.agregarItem(t1);
        hu1.agregarItem(t2);
        hu1.agregarItem(spike1);

        // 2. Otra Historia de Usuario con una Tarea
        var t3 = new Tarea("CRUD Libros", "Alta, baja y mod", Duration.ofHours(10));
        var hu2 = new HistoriaDeUsuario("HU2: Gestión de Libros");
        hu2.agregarItem(t3);

        // 3. Un Spike suelto
        var spike2 = new Spike("Spike: Pruebas de Carga", "Optimizar consultas", Duration.ofHours(5));
        spike2.agregarItem(new Tarea("Configurar JMeter", "Scripting inicial", Duration.ofHours(3)));

        // Agregar al proyecto
        proyectoScrum.agregarItem(hu1);
        proyectoScrum.agregarItem(hu2);
        proyectoScrum.agregarItem(spike2);

        //resultados
        System.out.println(proyectoScrum.mostrar());
        System.out.println("Duración total estimada del proyecto: " + proyectoScrum.calcularDuracion().toHours() + " hs");
    }
}
