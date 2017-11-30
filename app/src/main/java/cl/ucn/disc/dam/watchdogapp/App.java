package cl.ucn.disc.dam.watchdogapp;

import android.app.Application;

import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.apache.commons.lang3.time.StopWatch;

import cl.ucn.disc.dam.watchdogapp.dao.AppDatabase;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JOHN
 */
@Slf4j
public final class App extends Application {

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        // Timer
        final StopWatch stopWatch = StopWatch.createStarted();

        // DBFLow
        {
            // Initialize DBFLow
            FlowManager.init(FlowConfig.builder(this)
                    .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class)
                        .databaseName("controlvehicularstore")
                        .build())
                    // .openDatabasesOnInit(true)
                    .build()
            );
        }


        // Timming
        log.debug("Initialization in: {}", stopWatch);

    }

}
