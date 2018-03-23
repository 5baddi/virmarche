package info.baddi.virmarche.Fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import info.baddi.virmarche.R;

public class SettingsFragment extends Fragment
{
    public String oldPhoneNumber, oldCostAmount, oldNotif;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText phone = (EditText) view.findViewById(R.id.settingPhoneNumber);
        final EditText cost = (EditText) view.findViewById(R.id.settingSMSCost);
        final CheckBox notif = (CheckBox) view.findViewById(R.id.settingNotif);
        final Button save = (Button) view.findViewById(R.id.settingSave);


    }
}
