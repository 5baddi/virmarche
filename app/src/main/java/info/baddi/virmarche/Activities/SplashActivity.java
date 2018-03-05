package info.baddi.virmarche.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import info.baddi.virmarche.R;

public class SplashActivity extends AppCompatActivity {

    TextView splashTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashTitle = (TextView) findViewById(R.id.splashTitle);

        // Set Custom Font to Splash Title
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto-regular.ttf");
        splashTitle.setTypeface(robotoFont);

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                    startActivity(new Intent(getApplicationContext(), IdentificationActivity.class));
                    finish();
                }catch(InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
        };

        splashThread.start();
    }
}
