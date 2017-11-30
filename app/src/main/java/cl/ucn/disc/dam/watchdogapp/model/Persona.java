package cl.ucn.disc.dam.watchdogapp.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author JOHN
 */
@Builder
public final class Persona {

    @Getter
    private int rut;

    @Getter
    private String nombre;

    @Getter
    private String correoElectronico;

    @Getter
    private int telefono;

    @Getter
    private int numeroAnexo;

}
