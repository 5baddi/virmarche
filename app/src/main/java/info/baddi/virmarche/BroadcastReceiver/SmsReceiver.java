package info.baddi.virmarche.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import info.baddi.virmarche.Helpers.Command;
import info.baddi.virmarche.Helpers.Db;
import info.baddi.virmarche.Notifications.CurrentLocationNotification;
import info.baddi.virmarche.R;

public class SmsReceiver extends BroadcastReceiver
{
    private final SmsManager smsManager = SmsManager.getDefault();
    private Db db;

    public static int id = 1;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle extras = intent.getExtras();
        try
        {
            if(extras != null)
            {
                final Object[] smsContent = (Object[]) extras.get("pdus");
                for(Object sms : smsContent)
                {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms);

                    CurrentLocationNotification notif = new CurrentLocationNotification();
                    String phoneNumber = smsMessage.getOriginatingAddress();
                    String msgBody = smsMessage.getMessageBody();

                    Bundle values = Command.parseResponse(msgBody);
                    if(values != null)
                    {
                        int fragment = values.getInt("fragment");
                        values.putString("phone", phoneNumber);
                        values.putString("msg", msgBody);
                        values.putLong("timeStamp", smsMessage.getTimestampMillis());
                        notif.notify(context, fragment, values, id);
                    }
                    id++;
                }
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
