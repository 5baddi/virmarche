package info.baddi.virmarche.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import info.baddi.virmarche.Activities.MainActivity;
import info.baddi.virmarche.Activities.NetworkActivity;
import info.baddi.virmarche.Helpers.Info;
import info.baddi.virmarche.R;

/**
 * Created by 5Baddi on 06-Mar-18.
 */

public class NetworkReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(!Info.isOnline(context) && Info.appIsActive(context))
            Toast.makeText(context, context.getString(R.string.offline_msg), Toast.LENGTH_LONG).show();
        else if(Info.isOnline(context) && Info.appIsActive(context) && NetworkActivity.CONNECTIVITY_CHECK)
            context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
