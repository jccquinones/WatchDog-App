package cl.ucn.disc.dam.watchdogapp.tasks;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.List;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import org.apache.commons.lang3.time.StopWatch;

import cl.ucn.disc.dam.watchdogapp.controller.VehiculoController;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;

/**
 * @authors Jose Diaz , John Quiñonez
 */

@Slf4j
public final class GetSaveVehiculosTask extends AsyncTask<Void, Void, Integer> {

    /**
     *
     */
    private TaskListener taskListener;

    /**
     * @param taskListener
     */
    public GetSaveVehiculosTask(TaskListener taskListener) {
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

        // Getting from getVehiculoss
        final List<Vehiculo> vehiculos = getVehiculos();

        // Saving in database
        if (vehiculos != null && vehiculos.size() != 0) {

            log.debug("Saving {} Vehiculos in database ..", vehiculos.size());

            // Cronometro
            final StopWatch stopWatch = StopWatch.createStarted();

            final ModelAdapter<Vehiculo> modelAdapter = FlowManager.getModelAdapter(Vehiculo.class);

            int saved = 0;
            for (final Vehiculo vehiculo : vehiculos) {

                if (modelAdapter.exists(vehiculo)) {
                    continue;
                }

                modelAdapter.insert(vehiculo);
                saved++;

            }
            log.debug("Saved {} new Articles in {}.", saved, stopWatch);

            return saved;

            /*
            FastStoreModelTransaction<Article> fastStoreModelTransaction = FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(Article.class))
                    .addAll(articles).build();

            fastStoreModelTransaction.execute(FlowManager.getWritableDatabase(AppDatabase.class));
           */

        }

        return null;

    }

    /**
     * @return the {@link List} of {@link Vehiculo}.
     */
    private List<Vehiculo> getVehiculos() {

        final VehiculoController vehiculoController = new VehiculoController();

        try {
            // Obtengo los vehiculos
            return vehiculoController.getVehiculos("techcrunch,ars-technica,engadget,buzzfeed,wired");
        } catch (IOException e) {
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
         * Aviso que se termino la obtencion de los {@link Vehiculo}.
         */
        void taskFinished(final int newsVehiculos);

    }

}
