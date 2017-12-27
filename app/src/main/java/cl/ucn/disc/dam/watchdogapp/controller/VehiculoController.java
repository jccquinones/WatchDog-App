package cl.ucn.disc.dam.watchdogapp.controller;

/**
 * Created by eskal on 07/12/2017.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dam.watchdogapp.model.Persona;
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

        final Persona p = Persona.builder()
                .nombre("John")
                .rut(18853912)
                .telefono(992930248)
                .build();

        final Persona p1 = Persona.builder()
                .nombre("Jose")
                .rut(185932533)
                .telefono(99289998)
                .build();

        List vehiculo = new ArrayList();
        final Vehiculo v1 = Vehiculo.builder().color("rojo").anio(2010).marca("chevrolet").patente("A12").descripcion("D1D1D1D1D1D1D1D1D1D1").modelo("Camaro").dueno(p1).build();
        final Vehiculo v2 = Vehiculo.builder().color("azul").anio(2015).marca("suzuki").patente("A13").descripcion("D2D2D2D2D2D2D2D2D2D2").modelo("Celerio").dueno(p).build();
        final Vehiculo v3 = Vehiculo.builder().color("morado").anio(2017).marca("peugeot").patente("A14").descripcion("D3D3D3D3D3D3D3D3D3D3").modelo("3008").dueno(p).build();
        final Vehiculo v4 = Vehiculo.builder().color("blanca").anio(2008).marca("chevrolet").patente("AE-RL-13").descripcion("D4D4D4D4D4D4D4D4D4D4").modelo("Luv").dueno(p1).build();
        final Vehiculo v5 = Vehiculo.builder().color("gris").anio(1999).marca("volvo").patente("A16").descripcion("D5D5D5D5D5D5D5D5D5").modelo("v90").dueno(p1).build();
        final Vehiculo v6 = Vehiculo.builder().color("negro").anio(2000).marca("suzuki").patente("AB-KV-90").descripcion("NUEVO AUTO").modelo("Nomade").dueno(p1).build();
        vehiculo.clear();
        vehiculo.add(v1);
        vehiculo.add(v2);
        vehiculo.add(v3);
        vehiculo.add(v4);
        vehiculo.add(v5);
        vehiculo.add(v6);
        v6.save();
        return vehiculo;
    }
}

