package info.baddi.virmarche.Helpers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by 5Baddi on 05-Mar-18.
 */

public class UI
{
    public static void cleanInputs(ViewGroup viewGroup)
    {
        int index = 0;
        while (index < viewGroup.getChildCount())
        {
            View view = viewGroup.getChildAt(index);
            if(view instanceof EditText)
                ((EditText) view).setText("");

            if(view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) cleanInputs((ViewGroup) view);

            index++;
        }
    }
}
