package info.baddi.virmarche.Fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import info.baddi.virmarche.Helpers.Db;
import info.baddi.virmarche.R;

public class SettingsFragment extends Fragment
{
    private Db db;
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

        db = new Db(getActivity());

        Cursor phonePram = db.getData("name", "phone", db.SETTING_TABLE);
        if(phonePram != null && phonePram.moveToFirst())
        {
            oldPhoneNumber = phonePram.getString(phonePram.getColumnIndex("value"));
            phone.setText(oldPhoneNumber);
        }

        Cursor costParam = db.getData("name", "cost", db.SETTING_TABLE);
        if(costParam != null && costParam.moveToFirst())
        {
            oldCostAmount = costParam.getString(costParam.getColumnIndex("value"));
            cost.setText(oldCostAmount);
        }

        Cursor notifParam = db.getData("name", "notif", db.SETTING_TABLE);
        if(notifParam != null && notifParam.moveToFirst())
        {
            oldNotif = notifParam.getString(notifParam.getColumnIndex("value"));
            notif.setChecked(oldNotif.equals("1"));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues value = new ContentValues();
                ArrayList<ContentValues> values = new ArrayList<ContentValues>();

                oldPhoneNumber = (phone.getText().equals("") || phone.getText().equals(oldPhoneNumber)) ? "" : phone.getText().toString();
                value.put("name", "phone");
                value.put("value", oldPhoneNumber);
                values.add(value);

                oldCostAmount = (cost.getText().equals("") || cost.getText().equals(oldCostAmount)) ? null : cost.getText().toString();
                value = new ContentValues();
                value.put("name", "cost");
                value.put("value", oldCostAmount);
                values.add(value);

                oldNotif = notif.isChecked() ? "1" : "0";
                value = new ContentValues();
                value.put("name", "notif");
                value.put("value", oldNotif);
                values.add(value);

                if(db.updateBulkData(values, "name", Db.SETTING_TABLE)) Toast.makeText(getActivity(), "Your Setting saved successfully", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getActivity(), "Error ! try again please", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
