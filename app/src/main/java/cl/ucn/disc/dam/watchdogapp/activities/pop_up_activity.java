package cl.ucn.disc.dam.watchdogapp.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class pop_up_activity extends AppCompatActivity {
    TextView patente;
    TextView modelo;
    TextView marca;
    TextView color;
    TextView anio;
    TextView descripcion;
    TextView rut;
    TextView nombre;
    TextView fono;
    TextView anexo;
    TextView tipo;
    TextView cargo;
    TextView local;
    TextView correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_info);

        patente = (TextView) findViewById(R.id.ra_patente2);
        modelo = (TextView) findViewById(R.id.ra_modelo2);
        marca = (TextView) findViewById(R.id.ra_marca2);
        color = (TextView) findViewById(R.id.ra_color2);
        anio = (TextView) findViewById(R.id.ra_anio2);
        descripcion = (TextView) findViewById(R.id.ra_descripcion2);
        rut = (TextView) findViewById(R.id.ra_rut2);
        nombre = (TextView) findViewById(R.id.ra_nombre2);
        fono = (TextView) findViewById(R.id.ra_telefono2);
        anexo = (TextView) findViewById(R.id.ra_anexo2);
        tipo = (TextView) findViewById(R.id.ra_tipo2);
        cargo = (TextView) findViewById(R.id.ra_cargo2);
        local = (TextView) findViewById(R.id.ra_localizacion2);
        correo = (TextView) findViewById(R.id.ra_correo2);

        Vehiculo auto = (Vehiculo) getIntent().getExtras().getSerializable("autite");

        int anioAuto = auto.getAnio();
        String anioAutoStr = String.valueOf(anioAuto);
        patente.setText(auto.getPatente());
        modelo.setText(auto.getModelo());
        marca.setText(auto.getMarca());
        color.setText(auto.getColor());
        anio.setText(anioAutoStr);
        descripcion.setText(auto.getDescripcion());
        //int rut1 = auto.getDueno().getRut();
        //String rutStr = String.valueOf(rut1);
        //int fono1 = auto.getDueno().getTelefono();
        //String fonoStr = String.valueOf(fono1);
        //int anex1 = auto.getDueno().getNumeroAnexo();
        //String anexStr = String.valueOf(anex1);
        //rut.setText(rutStr);
        nombre.setText(auto.getDueno().getNombre());
        correo.setText(auto.getDueno().getCorreoElectronico());
        //fono.setText(fonoStr);
        //anexo.setText(anexStr);
        local.setText(auto.getDueno().getLocalizacion());
        tipo.setText(auto.getDueno().getTipo());
        cargo.setText(auto.getDueno().getCargo());
    }
}
