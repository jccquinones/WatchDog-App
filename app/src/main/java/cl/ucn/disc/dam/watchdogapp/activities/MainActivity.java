package cl.ucn.disc.dam.watchdogapp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.adapters.VehiculoAdapter;
import cl.ucn.disc.dam.watchdogapp.adapters.VehiculoDBFlowAdapter;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import cl.ucn.disc.dam.watchdogapp.tasks.GetSaveVehiculosTask;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MainActivity extends Activity implements GetSaveVehiculosTask.TaskListener {

    /**
     * Adapter de {@link cl.ucn.disc.dam.watchdogapp.model.Vehiculo}.
     */
    private BaseAdapter vehiculoAdapter;

    /**
     * Running background task
     */
    private GetSaveVehiculosTask getSaveVehiculosTask;

    // Declare Variables
    ListView list;
    EditText editsearch;
    String[] rank;
    String[] country;
    String[] population;
    ArrayList<Vehiculo> arraylist = new ArrayList<Vehiculo>();

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO : BORRAR TODO LO ETIQUETADO CON EJEMPLO DE LISTVIEW
        /* ESTE ES UN EJEMPLO DE LISTVIEW */
        // Generate sample data
        // Se generan la lista para colocar en listview
        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        country = new String[] { "China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan" };

        population = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };
        final Vehiculo v1 = Vehiculo.builder().color("rojo").anio(2010).marca("chevrolet").patente("A12").descripcion("D1D1D1D1D1D1D1D1D1D1").modelo("Camaro").build();
        final Vehiculo v2 = Vehiculo.builder().color("azul").anio(2015).marca("suzuki").patente("A13").descripcion("D2D2D2D2D2D2D2D2D2D2").modelo("Celerio").build();
        final Vehiculo v3 = Vehiculo.builder().color("morado").anio(2017).marca("peugeot").patente("A14").descripcion("D3D3D3D3D3D3D3D3D3D3").modelo("3008").build();
        final Vehiculo v4 = Vehiculo.builder().color("blanca").anio(2008).marca("chevrolet").patente("A15").descripcion("D4D4D4D4D4D4D4D4D4D4").modelo("Luv").build();
        final Vehiculo v5 = Vehiculo.builder().color("gris").anio(1999).marca("volvo").patente("A16").descripcion("D5D5D5D5D5D5D5D5D5").modelo("v90").build();

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.list_view);

        /* ESTO ES UN EJEMPLO DE LISTVIEW */
        arraylist.add(v1);
        arraylist.add(v2);
        arraylist.add(v3);
        arraylist.add(v4);
        arraylist.add(v5);


        // Mostrar la barrita
        /*final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            //actionBar.setLogo(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xFFFF0000, 0};
        this.getListView().setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.getListView().setDividerHeight(5);
        */
        // Adaptador de vehiculos
        //this.vehiculoAdapter = new VehiculoDBFlowAdapter(this);
        //super.setListAdapter(this.vehiculoAdapter);

        this.vehiculoAdapter = new VehiculoDBFlowAdapter(this);

        list.setAdapter(this.vehiculoAdapter);

        // Si no hay articulos en el adaptador (y por lo tanto en la base de datos) ..
        if (this.vehiculoAdapter.isEmpty()) {
            // .. ejecuto la tarea para obtenerlas.
            this.runGetAndSaveVehiculosTask();
        } else{
            // Locate the EditText in listview_main.xml
            editsearch = (EditText) findViewById(R.id.rv_filtro);

            // Capture Text in EditText
            editsearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                    //this.vehiculoAdapter.filter(text);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1,
                                              int arg2, int arg3) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                    // TODO Auto-generated method stub
                }
            });
        }



    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
     * depending on later user activity.
     * <p>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestart
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onDestroy
     */
    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * Metodo que realiza la ejecucion en segundo plano de la tarea que obtiene los
     * {@link cl.ucn.disc.dam.watchdogapp.model.Vehiculo} desde Internet.
     */
    private void runGetAndSaveVehiculosTask() {

        // Si ya hay una tarea de obtencion de articulos corriendo no ejecuto una nueva!
        if (this.getSaveVehiculosTask != null) {
            //Toast.makeText(this, "Already downloading Vehiculos ..", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show little message
        // Toast.makeText(this, "Downloading Vehiculos ..", Toast.LENGTH_LONG).show();

        // Inicio la tarea
        log.debug("Starting GetSaveVehiculosTask ..");
        this.getSaveVehiculosTask = new GetSaveVehiculosTask(this);
        this.getSaveVehiculosTask.execute();

    }

    /**
     * Aviso que se termino la obtencion de los {@link cl.ucn.disc.dam.watchdogapp.model.Vehiculo}.
     *
     * @param newsVehiculos numero de articulos nuevos obtenidos.
     */
    @Override
    public void taskFinished(int newsVehiculos) {
        // Show little message
        Toast.makeText(this, "New Vehiculos: " + newsVehiculos, Toast.LENGTH_LONG).show();

        log.debug("Finished!");
        this.vehiculoAdapter.notifyDataSetChanged();

        // Clean the task!
        this.getSaveVehiculosTask = null;
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_reload: {
                this.runGetAndSaveVehiculosTask();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
