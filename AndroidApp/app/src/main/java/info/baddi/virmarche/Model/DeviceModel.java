package info.baddi.virmarche.Model;

import android.text.TextUtils;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 5Baddi on 06-Mar-18.
 */

public class DeviceModel
{
    //@Exclude
    public String name;

    public String type = "Locator";
    public HashMap<String, String> properties;

    public DeviceModel(){}
}
