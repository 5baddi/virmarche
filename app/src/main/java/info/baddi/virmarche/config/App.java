package info.baddi.virmarche.config;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.firebase.client.Firebase;

/**
 * Created by 5Baddi on 03-Mar-18.
 */

public class App extends Application
{
    public final static String FIRE_DATABASE = "https://virmarche-c7a6e.firebaseio.com/";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

        if(preferences == null) preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(editor == null) editor = preferences.edit();


        if(!preferences.getBoolean("firstTime", false))
        {

        }
    }
}
