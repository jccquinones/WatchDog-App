package cl.ucn.disc.dam.watchdogapp.controller;

/**
 * Created by eskal on 07/12/2017.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class VehiculoController {


    /**
     * Obtencion de {@link Vehiculo}
     *
     * @return the {@link List} of {@link Vehiculo}.
     */
    public List<Vehiculo> getVehiculos(final String source) throws IOException {
        List vehiculo = new ArrayList();
        final Vehiculo v1 = Vehiculo.builder().color("rojo").anio(2010).marca("chevrolet").patente("A12").build();
        final Vehiculo v2 = Vehiculo.builder().color("azul").anio(2015).marca("suzuki").patente("A13").build();
        final Vehiculo v3 = Vehiculo.builder().color("morado").anio(2017).marca("peugeot").patente("A14").build();
        final Vehiculo v4 = Vehiculo.builder().color("verde").anio(2008).marca("chevrolet").patente("A15").build();
        final Vehiculo v5 = Vehiculo.builder().color("dorado").anio(1999).marca("volvo").patente("A16").build();

        vehiculo.add(v1);
        vehiculo.add(v2);
        vehiculo.add(v3);
        vehiculo.add(v4);
        vehiculo.add(v5);

        return vehiculo;
    }
}

