package com.gacha.quizapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gacha.quizapp.R;

import static android.widget.Toast.LENGTH_LONG;

public class SignInFragment extends Fragment {
    private View signInFragment;
    private EditText email, password;
    private Button btnSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signInFragment = inflater.inflate(R.layout.signin_fragment, container, false);

        email = signInFragment.findViewById(R.id.editTextTextEmailAddress);
        password = signInFragment.findViewById(R.id.editTextTextPassword);
        btnSignIn = signInFragment.findViewById(R.id.btnSignIn);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getActivity(), "Sign Up Successful!", LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Invalid email or password", LENGTH_LONG).show();
                }
            }
        });

        return signInFragment;
    }
}
