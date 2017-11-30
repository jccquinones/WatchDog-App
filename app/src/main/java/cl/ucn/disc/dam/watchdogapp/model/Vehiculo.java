package cl.ucn.disc.dam.watchdogapp.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author JOHN
 */
@Builder
public final class Vehiculo {

    @Getter
    private String patente;

    @Getter
    private String marca;

    @Getter
    private String color;

    @Getter
    private String modelo;

    @Getter
    private int anio;

    @Getter
    private String descripcion;

}
