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

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.adapters.VehiculoDBFlowAdapter;
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

    private ListView lv;

    private EditText et;

    private ArrayList<String> array_sort = new ArrayList<String>();
    int textlength = 0;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mostrar la barrita
        final ActionBar actionBar = super.getActionBar();
        if (actionBar != null) {
            //actionBar.setLogo(R.drawable.ic_launcher_foreground);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }

        // Row division
        int[] colors = {0, 0xFFFF0000, 0};
        this.lv.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        this.lv.setDividerHeight(5);

        // TODO : Arreglar adaptador cambiar listActivity a listView
        // Adaptador de vehiculos
        this.vehiculoAdapter = new VehiculoDBFlowAdapter(this);
        lv.setAdapter(this.vehiculoAdapter);

        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // Abstract Method of TextWatcher Interface.
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Abstract Method of TextWatcher Interface.
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textlength = et.getText().length();
                array_sort.clear();

                /*for (int i = 0; i < listview_array.length; i++) {
                    if (textlength <= listview_array[i].length()) {
                        if (et.getText().toString().equalsIgnoreCase((String) listview_array[i].subSequence(0, textlength))) {
                            array_sort.add(listview_array[i]);
                        }
                    }
                }*/

                lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, array_sort));
            }
        });
        // Si no hay articulos en el adaptador (y por lo tanto en la base de datos)
        if (this.vehiculoAdapter.isEmpty()) {
            // .. ejecuto la tarea para obtenerlas.
            this.runGetAndSaveVehiculosTask();
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
