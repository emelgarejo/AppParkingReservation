package pe.edu.upc.appparkingreservation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.edu.upc.appparkingreservation.R;

public class SplashScreenActivity extends AppCompatActivity {
    final static int SPLASH_OUT_TIMEOUT = 3500;
    final static int SLEEP_INTERVAL = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread splashThread = new Thread() {
            int wait = 0;
            @Override
            public void run() {
                try {
                    super.run();
                    while(wait < SPLASH_OUT_TIMEOUT) {
                        sleep(SLEEP_INTERVAL);
                        wait += SLEEP_INTERVAL;
                    }

                } catch (Exception e) {
                    System.out.println("EXc=" + e);

                } finally {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        splashThread.start();

    }
}
