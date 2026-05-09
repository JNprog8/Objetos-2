# Patrones de Diseño: Template Method y Composite

- **Patrones de Diseño**: Buenas soluciones a problemas recurrentes.
- Surgen a partir del libro de Christopher Alexander, "A Pattern Language: Towns, Buildings,
  Construction" (1977).
- Un patrón describe un problema recurrente en un determinado contexto y describe el núcleo de su solución, de manera
  que esta solución puede aplicarse de una manera distinta cada vez.
- **Reutilizar la experiencia** de quienes ya se han encontrado con problemas similares y han encontrado una buena
  solución.
- En OOP un patrón es una descripción de un problema de diseño y la solución, a la que se le da un nombre y que se puede
  aplicar en nuevos y diferentes contextos, con consejos de cómo aplicarlo.
- Elementos esenciales:
    - Nombre: incrementa nuestro vocabulario de diseño.
    - Propósito: objetivo, fin, utilidad.
    - Problema: ¿Cuándo aplicar el patrón?
    - Solucion: elementos que constituyen el diseño, relaciones, responsabilidades y colaboraciones.
    - Consecuencias: resultados, ventajas e inconvenientes.
- Libro: [Design Patterns](https://www.amazon.com/-/es/Erich-Gamma/dp/0201633612/ref=sr_1_1?adgrpid=83183444562)
- Template method
    - Propósito: define el esqueleto de un algoritmo en la superclase pero permite que las subclases sobrescriban pasos
      del algoritmo sin cambiar su estructura.
- Composite:
    - Propósito: permite componer objetos en estructuras de árbol y trabajar con esas estructuras como si fueran objetos
      individuales.
    - Permite a los clientes trabajar con objetos individuales o compuetos (conjuntos/colecciones) de forma
      transparente (polimórfica).
- Diagrama de Clases
    - Clases, relaciones de composición y herencia.
    - Nunca "cajitas vacías" sin comportamiento. Siempre con al menos el método polimórfico.