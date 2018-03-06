package info.baddi.virmarche.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import info.baddi.virmarche.Activities.MainActivity;
import info.baddi.virmarche.Activities.NetworkActivity;
import info.baddi.virmarche.Helpers.Info;
import info.baddi.virmarche.config.App;

/**
 * Created by 5Baddi on 06-Mar-18.
 */

public class NetworkReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Info.appIsActive(context)){
            if(!Info.isOnline(context))
                context.startActivity(new Intent(context, NetworkActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            else
                context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
}
