package info.baddi.virmarche.Helpers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by 5Baddi on 04-Mar-18.
 */

public class Info
{
    private Context context;

    public static String getPhoneNumber(Context context)
    {
        @SuppressLint("ServiceCast")
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
            return "";

        Log.i("Phone", telephonyManager.getLine1Number());
        return telephonyManager.getLine1Number();
    }
}
