package oop2.tp3.refactorizado.ejercicio2;

import oop2.tp3.refactorizado.ejercicio2.dominio.Recaudacion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "LifeLock");
        
        List<Map<String, String>> results = Recaudacion.where(options);
        
        System.out.println("Resultados para LifeLock:");
        for (Map<String, String> result : results) {
            System.out.println(result);
        }

        options.clear();
        options.put("city", "Tempe");
        results = Recaudacion.where(options);

        System.out.println("\nResultados para la ciudad Tempe:");
        for (Map<String, String> result : results) {
            System.out.println(result);
        }
    }
}