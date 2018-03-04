package info.baddi.virmarche.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import info.baddi.virmarche.R;

/**
 * Created by 5Baddi on 04-Mar-18.
 */

public class Command
{
    private final String getPosition = "smslink";

    private Context context;
    private SmsManager smsManager;
    private Db db;

    public final static int DEVICE_LOCATION = R.id.nav_locate;

    public Command(Context context)
    {
        if(this.context == null) this.context = context;
        if(smsManager == null) smsManager = SmsManager.getDefault();
        if(db == null) db = new Db(this.context);
    }

    // Just Device Id
    public void requestCurrentPosition(String devicePhoneNumber, String devicePassword)
    {
        Cursor settings = db.getData("name","phone", db.SETTING_TABLE);
        if(settings != null && settings.moveToFirst())
        {
            String phoneNumber = settings.getString(settings.getColumnIndex("value"));
            smsManager.sendTextMessage(devicePhoneNumber, phoneNumber, getPosition + devicePassword, null, null);
        }
    }

    private static int checkResponseType(String response)
    {
        // || (response.contains("lat") && response.contains("long") || response.contains("maps.google.com"))
        if(response.contains("lat:") && response.contains("long:"))
            return DEVICE_LOCATION; // Current Location

        return 0; // Unknown
    }

    public static Bundle parseResponse(String response)
    {

        Bundle value = null;
        int responseType = checkResponseType(response);
        if(responseType == 0) return value;

        int start = 0, end =  0;
        switch (responseType)
        {
            case DEVICE_LOCATION:
                start = response.indexOf("lat:");
                end = response.indexOf("T:");
                String needed = response.substring(start, end - 1);
                String[] locateInfo = needed.split(" ");

                value = new Bundle();
                value.putInt("fragment", DEVICE_LOCATION);
                for(String info : locateInfo)
                {
                    String[] infoData = info.split(":");
                    if(infoData.length == 2) value.putDouble(infoData[0], Double.parseDouble(infoData[1]));
                }
            break;
        }

        return value;
    }
}
