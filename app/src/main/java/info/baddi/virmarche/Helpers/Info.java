package info.baddi.virmarche.Helpers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;

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

    public static boolean isOnline(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();

        return (netInfo != null && netInfo.isConnected());
    }

    public static boolean appIsActive(Context context)
    {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                List<ActivityManager.RunningAppProcessInfo> runningProcess = activityManager.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcess) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName()))
                                return true;
                        }
                    }
                }
            } else {
                List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
                ComponentName componentName = runningTaskInfos.get(0).topActivity;
                if (componentName.getPackageName().equals(context.getPackageName()))
                    return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return false;
    }
}
