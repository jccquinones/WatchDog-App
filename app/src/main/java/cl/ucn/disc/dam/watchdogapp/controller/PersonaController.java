package cl.ucn.disc.dam.watchdogapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dam.watchdogapp.model.Persona;

/**
 * Jose Diaz, John Quiñones
 */

public class PersonaController {

    /**
     * @return the {@link List} of {@link Persona}.
     */
    public List<Persona> getPersonas() throws IOException {

        List<Persona> personas = new ArrayList<>();

        final Persona p = Persona.builder()
                .nombre("John")
                .rut(188979297)
                .telefono(992930248)
                .build();
        personas.add(p);

        final Persona p1 = Persona.builder()
                .nombre("Jose")
                .rut(185932533)
                .telefono(99289998)
                .build();
        personas.add(p1);

        return personas;
    }
}
