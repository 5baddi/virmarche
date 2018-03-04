package info.baddi.virmarche.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

import info.baddi.virmarche.Helpers.Session;
import info.baddi.virmarche.R;

public class SplashActivity extends AppCompatActivity {

    TextView splashTitle;
    private Session session;
    private HashMap<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new Session(getApplicationContext());
        userData = session.getUserSession();

        splashTitle = (TextView) findViewById(R.id.splashTitle);

        // Set Custom Font to Splash Title
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto-regular.ttf");
        splashTitle.setTypeface(robotoFont);

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    if(!session.checkUserSession()) intent = new Intent(getApplicationContext(), IdentificationActivity.class);
                    startActivity(intent);

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
