package info.baddi.virmarche.Helpers;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by 5Baddi on 05-Mar-18.
 */

public class Validator
{
    public static boolean phoneNumber(String phoneNumber)
    {
        if(TextUtils.isEmpty(phoneNumber))
            return false;

        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public static boolean userName(String username)
    {
        if(TextUtils.isEmpty(username))
            return false;

        return (Pattern.matches("^[a-zA-Z0-9]{5,}$", username));
    }
}
