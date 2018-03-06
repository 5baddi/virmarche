package info.baddi.virmarche.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.baddi.virmarche.Helpers.Info;
import info.baddi.virmarche.R;

public class NetworkActivity extends AppCompatActivity {

    public static boolean CONNECTIVITY_CHECK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    @Override
    protected void onStart() {
        super.onStart();

        CONNECTIVITY_CHECK = true;
        isOnline();
    }

    @Override
    protected void onResume() {
        super.onResume();

        isOnline();
    }

    @Override
    protected void onStop() {
        super.onStop();

        CONNECTIVITY_CHECK = false;
    }

    private void isOnline(){
        if(Info.isOnline(getApplicationContext())){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
