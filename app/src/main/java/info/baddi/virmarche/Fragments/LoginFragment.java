package info.baddi.virmarche.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import info.baddi.virmarche.Activities.MainActivity;
import info.baddi.virmarche.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FirebaseAuth lAuth;
    private ProgressBar logProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lAuth = FirebaseAuth.getInstance();

        final EditText userEmail = (EditText) view.findViewById(R.id.logEmail);
        final EditText userPass = (EditText) view.findViewById(R.id.logPassword);
        final Button login = (Button) view.findViewById(R.id.login);
        logProgress = (ProgressBar) view.findViewById(R.id.logProgress);

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
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(userPass.getText()))
                    login.setEnabled(true);
                else
                    login.setEnabled(false);
            }
        });

        userPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = editable.toString().trim();
                if(!TextUtils.isEmpty(pass) && !TextUtils.isEmpty(userEmail.getText()))
                    login.setEnabled(true);
                else
                    login.setEnabled(false);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString().trim();
                final String password = userPass.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userEmail.setError(getString(R.string.inc_email));
                    return;
                }else{
                    login.setEnabled(false);
                    logProgress.setVisibility(View.VISIBLE);

                    lAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }else{
                                login.setEnabled(true);
                                logProgress.setVisibility(View.GONE);
                                if(password.length() < 8){
                                    userPass.setError(getString(R.string.min_pass));
                                    return;
                                }
                                else{
                                    Toast.makeText(getActivity(), getString(R.string.not_connect), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        logProgress.setVisibility(View.GONE);
    }
}
