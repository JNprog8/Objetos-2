package punto6.main;

import punto6.model.Orden;
import punto6.model.builder.TortaBuilder;

/**
 * La panadería El Cristal ofrece dos tipos de tortas, sabor chocolate y vainilla.
 * En su sistema para calcular el costo se encuentran las siguientes clases. Ahora quieren
 * empezar a vender tortas más completas. Modifica los fuentes utilizando el patrón decorador
 * para implementar los siguiente:
 * ● Agregado de baño en chocolate. A la descripción se le suma “con baño de chocolate”
 * y al precio se le suma el 15% más de lo que cuesta la torta.
 * ● Agregado de rocklets. A la descripción se le suma “con rocklets” y al precio se le
 * suma el $5 más de lo que cuesta la torta.
 * ● Agregar una torta de bizcochuelo de frutilla que cuesta el doble de la torta de vainilla
 * o chocolate.
 */
public class Main {

    public static void main(String[] args) {

        var orden = new Orden();

        // Torta 1
        var torta1 = new TortaBuilder()
                .tortaBaseChocolate()
                .conBanioChocolate()
                .conRocklets()
                .build();

        // Torta 2
        var torta2 = new TortaBuilder()
                .tortaBaseFrutilla()
                .conRocklets()
                .conRocklets()
                .conBanioChocolate()
                .build();

        // Torta 3
        var torta3 = new TortaBuilder()
                .tortaBaseVainilla()
                .conBanioChocolate()
                .build();

        orden.agregarTorta(torta1);
        orden.agregarTorta(torta2);
        orden.agregarTorta(torta3);

        orden.imprimirOrden();
    }
}
