package info.baddi.virmarche.config;

import android.app.Application;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import info.baddi.virmarche.Helpers.Db;
import info.baddi.virmarche.Helpers.Info;

/**
 * Created by 5Baddi on 03-Mar-18.
 */

public class App extends Application
{
    public final static String database = "freevirtra";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        if(preferences == null) preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(editor == null) editor = preferences.edit();


        if(!preferences.getBoolean("firstTime", false))
        {
            Db db = new Db(this);

            ArrayList<ContentValues> values = new ArrayList<ContentValues>();
            String phoneNumber = Info.getPhoneNumber(this);
            ContentValues value = new ContentValues();
            value.put("name", "phone");
            value.put("value", phoneNumber);
            values.add(value);

            value = new ContentValues();
            value.put("name", "notif");
            value.put("value", "1");
            values.add(value);

            value = new ContentValues();
            value.put("name", "cost");
            value.put("value", "100");
            values.add(value);

            db.putBulkData(values, Db.SETTING_TABLE);
        }
    }
}
