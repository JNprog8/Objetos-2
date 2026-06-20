package punto3.main;

/**
 * Toda golondrina es capaz de volar y comer y posee una energía medida en joules, la cual va
 * variando a medida que come o vuela. Al nacer, su energía es de 45 Joules. Cuando vuela, consume
 * un joule por cada kilómetro volado, más 10 joules fijos en cada vuelo debido al esfuerzo por
 * comenzar a volar. Cuando come, recupera 5 joules por cada gramo que come.
 * A las golondrinas les gusta realizar su deseo. Cuando se sienten eufóricas, su deseo es volar 5
 * kilómetros, como un paseo, y volver a su lugar de origen. Cuando se sienten débiles, su deseo es
 * comer (con 50 gramos de comida es suficiente) para recuperar energía y estar en condiciones de
 * volar cada vez que sea necesario. A medida que vuelan, las golondrinas consumen energía y se
 * van cansando. Digamos que se sienten débiles cuando su energía está por debajo de los 50 joules.
 * Cuando superan los 500 joules, se ponen eufóricas.
 * Implementar utilizando el patrón State y realice un diagrama de clases. En este caso particular, la
 * implementación será sin persistir el estado (como se sienten las golondrinas) es decir, no habrá una
 * variable de instancia estadoGolondrina. La única variable de instancia será su energía y el estado
 * o como se siente la golondrina se calculará en función de su energía. En el repo ya existe parte de
 * la implementación.
 * Fuente: Ejercicio obtenido de la cátedra de paradigmas de la UTN.
 */
public class Main {
}
