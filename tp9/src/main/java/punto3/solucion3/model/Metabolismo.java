package punto3.solucion3.model;


//Strategy: la reglas de LN - cada estrategia hacia la energia
//permite que cada golondrina procese la energia sin entrar en su clase
public interface Metabolismo {
    int gastoVolar(int kilometros);

    int beneficioComer(int gramos);
}
