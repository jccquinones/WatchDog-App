package cl.ucn.disc.dam.watchdogapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.ucn.disc.dam.watchdogapp.model.Persona;
import cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;

/**
 * Created by eskal on 27/12/2017.
 */

public class RegistroController {
    public List<RegistroIngreso> getRegistros(final String source) throws IOException {
        List registro = new ArrayList();
        Calendar c = Calendar.getInstance();

        final RegistroIngreso r1 = RegistroIngreso.builder().fecha(c.getTime()).porteria("Porteria Sur").patenteVehiculo("A1273").build();
        registro.add(r1);
        return registro;
    }
}
