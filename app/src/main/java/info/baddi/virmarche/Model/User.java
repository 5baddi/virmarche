package info.baddi.virmarche.Model;

import android.content.ContentValues;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by 5Baddi on 05-Mar-18.
 */

@IgnoreExtraProperties
public class User
{
    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String phoneNumber;

    public User(){}
}
