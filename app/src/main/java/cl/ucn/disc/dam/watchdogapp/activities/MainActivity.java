package cl.ucn.disc.dam.watchdogapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.tasks.GetSavePersonasTask;

public class MainActivity extends AppCompatActivity {


    /**
     * Running background task
     */
    private GetSavePersonasTask getSavePersonasTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
