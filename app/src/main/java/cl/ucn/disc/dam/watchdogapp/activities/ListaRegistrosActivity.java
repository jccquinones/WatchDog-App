package cl.ucn.disc.dam.watchdogapp.activities;

/**
 * Created by eskal on 27/12/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.adapters.RegistroIngresoDBFlowAdapter;
import cl.ucn.disc.dam.watchdogapp.adapters.RegistroIngresoDBFlowAdapter;
import cl.ucn.disc.dam.watchdogapp.model.Persona;
import cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso;
import cl.ucn.disc.dam.watchdogapp.tasks.GetSaveRegistrosTask;
import lombok.extern.slf4j.Slf4j;

/**
 * Jose Diaz, John Quiñones
 */
@Slf4j
public final class ListaRegistrosActivity extends AppCompatActivity implements GetSaveRegistrosTask.TaskListener {

    /**
     * Adapter de {@link cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso}.
     */
    private RegistroIngresoDBFlowAdapter registroAdapter;

    /**
     * Adapter de {@link cl.ucn.disc.dam.watchdogapp.model.Persona}.
     */
    private BaseAdapter personaAdapter;

    /**
     * Running background task
     */
    private GetSaveRegistrosTask getSaveRegistrosTask;

    // Declare Variables
    ListView list;
    EditText editsearch;
    ArrayList<RegistroIngreso> arraylist = new ArrayList<RegistroIngreso>();

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_registro);


        this.registroAdapter = new RegistroIngresoDBFlowAdapter(this);

        list.setAdapter(this.registroAdapter);

        // Si no hay articulos en el adaptador (y por lo tanto en la base de datos) ..
        if (this.registroAdapter.isEmpty()) {
            // .. ejecuto la tarea para obtenerlas.
            this.runGetAndSaveRegistrosTask();
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
     * {@link cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso} desde Internet.
     */
    private void runGetAndSaveRegistrosTask() {

        // Si ya hay una tarea de obtencion de articulos corriendo no ejecuto una nueva!
        if (this.getSaveRegistrosTask != null) {
            //Toast.makeText(this, "Already downloading Registros ..", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show little message
        // Toast.makeText(this, "Downloading Registros ..", Toast.LENGTH_LONG).show();

        // Inicio la tarea
        log.debug("Starting GetSaveRegistrosTask ..");
        this.getSaveRegistrosTask = new GetSaveRegistrosTask(this);
        this.getSaveRegistrosTask.execute();

    }

    /**
     * Aviso que se termino la obtencion de los {@link cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso}.
     *
     * @param newsRegistros numero de articulos nuevos obtenidos.
     */
    @Override
    public void taskFinished(int newsRegistros) {
        // Show little message
        Toast.makeText(this, "New Registros: " + newsRegistros, Toast.LENGTH_LONG).show();

        log.debug("Finished!");
        this.registroAdapter.notifyDataSetChanged();

        // Clean the task!
        this.getSaveRegistrosTask = null;
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
                this.runGetAndSaveRegistrosTask();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);

    }
}
