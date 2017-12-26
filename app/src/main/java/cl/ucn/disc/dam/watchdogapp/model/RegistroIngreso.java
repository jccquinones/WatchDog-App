package cl.ucn.disc.dam.watchdogapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;

import cl.ucn.disc.dam.watchdogapp.dao.AppDatabase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jose Diaz, John Quiñonez
 */
@Builder
@Table(database = AppDatabase.class)
@AllArgsConstructor
@NoArgsConstructor
public final class RegistroIngreso {

    //Número de estacionamiento
    @PrimaryKey(autoincrement = true)
    @Getter
    @Setter
    int NumEst;

    @Getter
    @Column
    String porteria;

    @Getter
    Date fecha;

}
