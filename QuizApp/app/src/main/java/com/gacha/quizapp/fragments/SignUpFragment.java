package com.gacha.quizapp.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gacha.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private EditText uName, uEmail, uPassword, uConfirmPass;
    private Button btnSignUp;
    private View signUpFragment;
    private FirebaseFirestore firebaseFirestore;
    private String userID;

    private static final String TAG = SignUpFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signUpFragment = inflater.inflate(R.layout.signup_fragment, container, false);

        uName = signUpFragment.findViewById(R.id.editTextTextPersonName);
        uEmail = signUpFragment.findViewById(R.id.editTextTextEmailAddress2);
        uPassword = signUpFragment.findViewById(R.id.editTextTextPassword2);
        uConfirmPass = signUpFragment.findViewById(R.id.editTextTextPassword3);
        btnSignUp = signUpFragment.findViewById(R.id.btnSignUp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = uName.getText().toString();
                String email = uEmail.getText().toString().trim();
                String password = uPassword.getText().toString().trim();
                String confirmPassword = uConfirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    uName.setError("Please enter your name");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    uEmail.setError("Please enter your email");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    uPassword.setError("Please enter your password");
                    return;
                }
                if(password.length() < 6){
                    uPassword.setError("Password must be >= 6 characters");
                    return;
                }
                if (!confirmPassword.equals(password)){
                    uConfirmPass.setError("Password not matched");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "User created", Toast.LENGTH_LONG).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("userName", name);
                            user.put("email", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("text", "user is created " + userID);
                                }
                            });
                        }else {
                            Toast.makeText(getActivity(), "Sign Up failed" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return signUpFragment;
    }
}
