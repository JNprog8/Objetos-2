package ejercicio2.controller;

import ejercicio2.aspect.Log;
import ejercicio2.model.Concurso;
import ejercicio2.model.InscripcionService;

import java.util.List;

public class InscripcionController {
    private InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    @Log
    public void saveInscription(String dni, String nom, String ape, String tel, String mail, Concurso c) {
        service.registrarNuevaInscripcion(dni, nom, ape, tel, mail, c);
    }

    @Log
    public List<Concurso> todosLosConcursos() {
        return service.concursosDisponibles();
    }
}
