package oop2.tp3.refactorizado.ejercicio2.dominio;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Investment {
    public static final String PERMALINK = "permalink";
    public static final String COMPANY_NAME = "company_name";
    public static final String NUMBER_EMPLOYEES = "number_employees";
    public static final String CATEGORY = "category";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String FUNDED_DATE = "funded_date";
    public static final String RAISED_AMOUNT = "raised_amount";
    public static final String RAISED_CURRENCY = "raised_currency";
    public static final String ROUND = "round";

    private final Map<String, String> atributos;

    // mapa de atributos evita depender de índices
    // del array u orden del archivo
    public Investment(Map<String, String> atributos) {
        this.atributos = Collections.unmodifiableMap(new HashMap<>(atributos));
    }


    public boolean cumpleCon(String campo, String valorEsperado) {
        if (valorEsperado == null || valorEsperado.isEmpty()) {
            return true;
        }
        String valorReal = atributos.get(campo);
        return valorReal != null && valorReal.equalsIgnoreCase(valorEsperado);
    }

    public Map<String, String> asMap() {
        return this.atributos;
    }

    // Getters de la logica de negocio
    public String getNombreEmpresa() {
        return atributos.get(COMPANY_NAME);
    }

    public String getCiudad() {
        return atributos.get(CITY);
    }
}