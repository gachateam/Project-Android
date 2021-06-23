package com.gacha.quizapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gacha.quizapp.R;
import com.gacha.quizapp.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_LONG;

public class SignInFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private View signInFragment;
    private EditText iEmail, iPassword;
    private Button btnSignIn;
    private TextView fPass;

    private static final String TAG = SignInFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        signInFragment = inflater.inflate(R.layout.signin_fragment, container, false);

        iEmail = signInFragment.findViewById(R.id.editTextTextEmailAddress);
        iPassword = signInFragment.findViewById(R.id.editTextTextPassword);
        btnSignIn = signInFragment.findViewById(R.id.btnSignIn);
        fPass = signInFragment.findViewById(R.id.resetPass);

        firebaseAuth = FirebaseAuth.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = iEmail.getText().toString().trim();
                String password = iPassword.getText().toString().trim();
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
                            startActivity(new Intent(SignInFragment.this.getActivity(), StartActivity.class));
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), "Sign Up failed " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        fPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordReset = new AlertDialog.Builder(v.getContext());
                passwordReset.setTitle("Reset Password ?");
                passwordReset.setMessage("Enter your email to reset password");
                passwordReset.setView(resetMail);

                passwordReset.setPositiveButton("Yes", (dialog, which) -> {
                    String mail = resetMail.getText().toString();
                    Log.d(TAG, "onClick: " + (mail.equals("")));
                    if (mail.equals("")){
                        Toast.makeText(getActivity(), "Please enter your email", LENGTH_LONG).show();
                    }
                    else {
                        firebaseAuth.sendPasswordResetEmail(mail)
                                .addOnSuccessListener(aVoid -> Toast.makeText(SignInFragment.this.getActivity(), "Reset link sented to your email", LENGTH_LONG).show())
                                .addOnFailureListener(e -> Toast.makeText(SignInFragment.this.getActivity(), "Can't send the link to your email", LENGTH_LONG).show());
                    }
                });

                passwordReset.setNegativeButton("No", (dialog, which) -> dialog.cancel());

                passwordReset.create().show();
            }
        });

        return signInFragment;


    }
}
