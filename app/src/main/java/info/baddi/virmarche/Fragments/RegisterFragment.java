package info.baddi.virmarche.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

import info.baddi.virmarche.Activities.MainActivity;
import info.baddi.virmarche.Helpers.UI;
import info.baddi.virmarche.Helpers.Validator;
import info.baddi.virmarche.Model.User;
import info.baddi.virmarche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    private FirebaseAuth rAuth;
    private DatabaseReference rDatabase;
    private ProgressBar regProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rAuth = FirebaseAuth.getInstance();
        rDatabase = FirebaseDatabase.getInstance().getReference();

        final EditText firstName = (EditText) view.findViewById(R.id.regFirstName);
        final EditText lastName = (EditText) view.findViewById(R.id.regLastName);
        final EditText userName = (EditText) view.findViewById(R.id.regUserName);
        final EditText userEmail = (EditText) view.findViewById(R.id.regEmail);
        final EditText userPassword = (EditText) view.findViewById(R.id.regPassword);
        final EditText userPhone = (EditText) view.findViewById(R.id.regPhone);
        final Button registerBtn = (Button) view.findViewById(R.id.register);
        regProgress = (ProgressBar) view.findViewById(R.id.regProgress);

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String firstName = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(userEmail.getText()) && !TextUtils.isEmpty(userPassword.getText()) && !TextUtils.isEmpty(userPhone.getText()))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String lastName = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(userEmail.getText()) && !TextUtils.isEmpty(userPassword.getText()) && !TextUtils.isEmpty(userPhone.getText()))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String userName = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail.getText()) && !TextUtils.isEmpty(userPassword.getText()) && !TextUtils.isEmpty(userPhone.getText()))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(userPassword.getText()) && !TextUtils.isEmpty(userPhone.getText()))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        userPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(userEmail.getText()) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(userPhone.getText()))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String phoneNumber = editable.toString().trim();
                if(!TextUtils.isEmpty(firstName.getText()) && !TextUtils.isEmpty(lastName.getText()) && !TextUtils.isEmpty(userName.getText()) && !TextUtils.isEmpty(userEmail.getText()) && !TextUtils.isEmpty(userPassword.getText()) && !TextUtils.isEmpty(phoneNumber))
                    registerBtn.setEnabled(true);
                else
                    registerBtn.setEnabled(false);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if(TextUtils.isEmpty(firstName.getText()) || TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(userName.getText()) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(userPhone.getText())){
                    Toast.makeText(getActivity(), getString(R.string.empty_fields), Toast.LENGTH_LONG).show();
                    return;
                }
                else if(!Validator.userName(userName.getText().toString())){
                    userName.setError(getString(R.string.inc_username_format));
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    userEmail.setError(getString(R.string.inc_email));
                    return;
                }
                else if(password.length() < 8){
                    userPassword.setError(getString(R.string.min_pass));
                    return;
                }
                else if(Validator.phoneNumber(userPhone.getText().toString())){
                    userPhone.setError(getString(R.string.inc_phone_format));
                    return;
                }
                else
                    regProgress.setVisibility(View.VISIBLE);
                    registerBtn.setEnabled(false);

                    rAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        User user = new User();
                                        user.firstName = firstName.getText().toString();
                                        user.lastName = lastName.getText().toString();
                                        user.username = userName.getText().toString();
                                        user.phoneNumber = userPhone.getText().toString();
                                        user.email = email;

                                        rDatabase.child("users").child(rAuth.getCurrentUser().getUid()).setValue(user);
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                    }
                                    else{
                                        regProgress.setVisibility(View.GONE);
                                        registerBtn.setEnabled(true);
                                    }
                                }
                            });
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        regProgress.setVisibility(View.GONE);
    }
}
