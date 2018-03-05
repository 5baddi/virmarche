package info.baddi.virmarche.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.HashMap;

/**
 * Created by 5Baddi on 01-Mar-18.
 */

public class Session
{
    private Context context;
    private SharedPreferences prefs;
    private Editor editor;

    public Session(Context context)
    {
        if(this.context == null) this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        editor = prefs.edit();
    }

    public void setUserSession(int id, String username, String hash)
    {
        editor.putInt("userId", id);
        editor.putString("userName", username);
        editor.putString("userHash", hash);
        editor.commit(); // Commit Changes
    }

    public HashMap<String, String> getUserSession()
    {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put("userId", String.valueOf(prefs.getInt("userId", 0)));
        userData.put("userName", prefs.getString("userName", ""));
        userData.put("userHash", prefs.getString("userHash", ""));

        return userData;
    }

    public static boolean checkUserSession(Context context)
    {
        Session session = new Session(context);
        HashMap<String, String> userData = session.getUserSession();
        if(userData.get("userId") != "0" || userData.get("userName").length() > 0 || userData.get("userHash").length() > 0) return true;

        return false;
    }

    public void destroyUserSession()
    {
        editor.remove("userId");
        editor.remove("userName");
        editor.remove("userHash");
        editor.commit();
    }


}
