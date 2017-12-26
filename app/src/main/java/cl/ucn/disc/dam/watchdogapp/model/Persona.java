package cl.ucn.disc.dam.watchdogapp.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

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
public final class Persona extends BaseModel implements Serializable{

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

    @Getter
    @Column
    String localizacion;

    @Getter
    @Column
    String cargo;

    @Getter
    @Column
    String tipo;


}
