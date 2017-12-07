package cl.ucn.disc.dam.watchdogapp.modelTest;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import cl.ucn.disc.dam.watchdogapp.App;
import cl.ucn.disc.dam.watchdogapp.dao.AppDatabase;
import cl.ucn.disc.dam.watchdogapp.model.Persona;

/**
 * Created by JOHN on 07-12-2017.
 */

public class PersonaTest {

    @Test
    public void testContructor() {

        final Persona persona = Persona.builder()
                .nombre("John")
                .rut(188979297)
                .telefono(992930248)
                .build();


        Assertions.assertThat(persona)
                .isNotNull();

        Assertions.assertThat(persona.getNombre())
                .isNotBlank();

    }

    @Test
    public void testInsertPersonaToDB(){

        final Persona persona = Persona.builder()
                .nombre("John")
                .rut(188979297)
                .telefono(992930248)
                .build();

        //FlowManager.getModelAdapter(Persona.class).insert(persona);

    }
}
