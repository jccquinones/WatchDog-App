package cl.ucn.disc.dam.watchdogapp.tasks;

import android.os.AsyncTask;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dam.watchdogapp.controller.PersonaController;
import cl.ucn.disc.dam.watchdogapp.model.Persona;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by JOHN on 07-12-2017.
 */
@Slf4j
public final class GetSavePersonasTask extends AsyncTask<Void, Void, Integer> {

    /**
     * Listener de las tareas a terminar.
     */
    private TaskListener taskListener;

    /**
     * @param taskListener
     */
    public GetSavePersonasTask(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Integer doInBackground(Void... voids) {

        // Getting from Internet
        final List<Persona> Personas = getPersonas();

        // Saving in database
        if (Personas != null && Personas.size() != 0) {

            log.debug("Saving {} Personas in database ..", Personas.size());

            // Cronometro
            final StopWatch stopWatch = StopWatch.createStarted();

            final ModelAdapter<Persona> modelAdapter = FlowManager.getModelAdapter(Persona.class);

            // Contador de nuevas noticias
            int saved = 0;
            for (final Persona Persona : Personas) {

                // Si la noticia ya existe, no es necesario almacenar nada.
                if (modelAdapter.exists(Persona)) {
                    continue;
                }

                // Inserto en la base de datos y cuento 1 mas.
                modelAdapter.insert(Persona);
                saved++;

            }
            log.debug("Saved {} new Personas in {}.", saved, stopWatch);

            return saved;

            /*
            // Version mas rapida de almacenaje, pero sin contar las inserciones.
            FastStoreModelTransaction<Persona> fastStoreModelTransaction = FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(Persona.class))
                    .addAll(Personas).build();
            fastStoreModelTransaction.execute(FlowManager.getWritableDatabase(AppDatabase.class));
           */

        }

        return null;

    }

    /**
     * @return the {@link List} of {@link Persona}.
     */
    private List<Persona> getPersonas() {

        // FIXME: Sera atributo de la clase?
        final PersonaController PersonaController = new PersonaController();

        try {
            // Obtengo los Personas desde internet via el controlador
            return PersonaController.getPersonas();
        } catch (IOException e) {
            log.error("Error", e);
            return null;
        }
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     * <p>
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param integer The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(Integer integer) {

        if (taskListener != null) {
            taskListener.taskFinished(integer);
        }

    }

    /**
     *
     */
    public interface TaskListener {

        /**
         * Aviso que se termino la obtencion de los {@link Persona}.
         */
        void taskFinished(final Integer newsPersonas);

    }
    
}
