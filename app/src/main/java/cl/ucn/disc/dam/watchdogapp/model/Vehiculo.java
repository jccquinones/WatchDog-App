package cl.ucn.disc.dam.watchdogapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.Serializable;

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
public final class Vehiculo implements Serializable {

    @PrimaryKey
    @Getter
    String patente;

    @Getter
    @Column
    String marca;

    @Getter
    @Column
    String color;

    @Getter
    @Column
    String modelo;

    @Getter
    @Column
    int anio;

    @Getter
    @Column
    String descripcion;

    @Getter
    @Column
    int rut_dueno;



}
