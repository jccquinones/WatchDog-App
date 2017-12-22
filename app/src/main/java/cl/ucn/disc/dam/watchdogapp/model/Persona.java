package cl.ucn.disc.dam.watchdogapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import cl.ucn.disc.dam.watchdogapp.dao.AppDatabase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jose Diaz, John Quiñonez
 */
@Builder
@Table(database = AppDatabase.class)
@AllArgsConstructor
@NoArgsConstructor
public final class Persona {

    @PrimaryKey
    @Getter
    int rut;

    @Getter
    @Column
    String nombre;

    @Getter
    @Column
    String correoElectronico;

    @Getter
    @Column
    int telefono;

    @Getter
    @Column
    int numeroAnexo;

}
