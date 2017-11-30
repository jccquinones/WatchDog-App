package cl.ucn.disc.dam.watchdogapp.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

/**
 * @author JOHN
 */
@Builder
public final class RegistroIngreso {

    @Getter
    private String porteria;

    @Getter
    private Date fecha;

}
