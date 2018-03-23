package info.baddi.virmarche.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import info.baddi.virmarche.Helpers.Info;
import info.baddi.virmarche.R;
import info.baddi.virmarche.config.App;

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
                    sleep(App.SPLASH_DURATION);
                    if(Info.isOnline(getApplicationContext()))
                        startActivity(new Intent(getApplicationContext(), IdentificationActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(), NetworkActivity.class));
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
