package info.baddi.virmarche.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import info.baddi.virmarche.Helpers.Validator;
import info.baddi.virmarche.Model.Device;
import info.baddi.virmarche.Model.DeviceModel;
import info.baddi.virmarche.R;

/**
 * Created by 5Baddi on 06-Mar-18.
 */

public class DeviceFragment extends Fragment
{
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        /*DeviceModel deviceType = new DeviceModel();
        deviceType.name = "TK102B";
        HashMap<String, String> properties = new HashMap<>();
        properties.put("network", "GSM|GPRS");
        properties.put("brand", "850|900|1800|1900MHz");
        deviceType.properties = properties;
        database.child("app").child("devices").push().setValue(deviceType);*/

        final Button addDeviceBtn = (Button) view.findViewById(R.id.addDeviceBtn);

        addDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_new_device);
                dialog.show();

                final EditText name = dialog.findViewById(R.id.deviceName);
                final EditText imei = dialog.findViewById(R.id.deviceImei);
                final EditText phone = dialog.findViewById(R.id.deviceSIMNumber);
                final Button save = dialog.findViewById(R.id.saveDeviceBtn);
                final ProgressBar saveProgress = dialog.findViewById(R.id.addProgress);
                final Spinner deviceModel = dialog.findViewById(R.id.deviceModel);


                //deviceModel.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, firebaseHelper.setSpinnerData(new String[]{"app", "devices"}, DeviceModel.class, "name", getString(R.string.device_model))));

                save.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Toast.makeText(getActivity(), deviceModel.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                       if(TextUtils.isEmpty(name.getText().toString())){
                           name.setError(getString(R.string.device_name_error));
                            return;
                       }else if(TextUtils.isEmpty(imei.getText().toString()) || imei.getText().length() != 15){
                           imei.setError(getString(R.string.device_imei_error));
                           return;
                       }else if(!Validator.phoneNumber(phone.getText().toString())) {
                           phone.setError(getString(R.string.device_sim_error));
                           return;
                       }else{
                           save.setEnabled(false);
                           saveProgress.setVisibility(View.VISIBLE);

                           Device device = new Device();
                           device.name = name.getText().toString();
                           device.imei = imei.getText().toString();
                           device.simNumber = phone.getText().toString();

                           DatabaseReference refDevice = database.child("users").child(auth.getCurrentUser().getUid()).child("devices").push();
                           refDevice.setValue(device, new DatabaseReference.CompletionListener() {
                               @Override
                               public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if(databaseError == null){
                                        dialog.hide();
                                        String deviceKey = databaseReference.getKey();
                                    }else{
                                        Toast.makeText(getActivity(), getString(R.string.device_failed), Toast.LENGTH_SHORT).show();
                                        save.setEnabled(true);
                                        saveProgress.setVisibility(View.GONE);
                                    }
                               }
                           });
                       }
                   }
                });
            }
        });
    }
}
