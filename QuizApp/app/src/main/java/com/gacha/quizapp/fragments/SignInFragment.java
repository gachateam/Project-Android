package com.gacha.quizapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gacha.quizapp.NavigationActivity;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_LONG;

public class SignInFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private View signInFragment;
    private EditText iEmail, iPassword;
    private Button btnSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signInFragment = inflater.inflate(R.layout.signin_fragment, container, false);

        iEmail = signInFragment.findViewById(R.id.editTextTextEmailAddress);
        iPassword = signInFragment.findViewById(R.id.editTextTextPassword);
        btnSignIn = signInFragment.findViewById(R.id.btnSignIn);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = iEmail.getText().toString().trim();
                String password = iPassword.getText().toString().trim();

                firebaseAuth = FirebaseAuth.getInstance();
//                if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                    Toast.makeText(getActivity(), "Sign Up Successful!", LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getActivity(), "Invalid email or password", LENGTH_LONG).show();
//                }
                if (TextUtils.isEmpty(email)){
                    iEmail.setError("Please enter your email");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    iPassword.setError("Please enter your password");
                    return;
                }
                if(password.length() < 6){
                    iPassword.setError("Password must be >= 6 characters");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Sign In successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getActivity(), StartActivity.class));
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), "Sign Up failed " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return signInFragment;
    }
}
