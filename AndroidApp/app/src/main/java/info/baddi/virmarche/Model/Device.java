package info.baddi.virmarche.Model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by 5Baddi on 06-Mar-18.
 */

@IgnoreExtraProperties
public class Device
{
    public String name;
    public String imei;
    public String simNumber;

    public Device(){}
}
