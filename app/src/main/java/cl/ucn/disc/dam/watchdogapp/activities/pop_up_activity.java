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

        Vehiculo auto = (Vehiculo) getIntent().getExtras().getSerializable("autite");

        int anioAuto = auto.getAnio();
        String anioAutoStr = String.valueOf(anioAuto);
        patente.setText(auto.getPatente());
        modelo.setText(auto.getModelo());
        marca.setText(auto.getMarca());
        color.setText(auto.getColor());
        anio.setText(anioAutoStr);
        descripcion.setText(auto.getDescripcion());
    }
}
