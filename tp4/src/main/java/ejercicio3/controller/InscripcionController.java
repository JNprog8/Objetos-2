package ejercicio3.controller;

import ejercicio3.model.Concurso;
import ejercicio3.model.InscripcionService;
import java.util.List;

public class InscripcionController {
    private InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    public void procesarInscripcion(String dni, String nom, String ape, String tel, String mail, Concurso c) {
        service.registrarNuevaInscripcion(dni, nom, ape, tel, mail, c);
    }

    public List<Concurso> mostrarConcursosDisponibles() {
        return service.concursosDisponibles();
    }
}
