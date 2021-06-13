package com.gacha.quizapp.ui.home;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.facebook.login.LoginManager;
import com.gacha.quizapp.NavigationActivity;
import com.gacha.quizapp.R;
import com.gacha.quizapp.SignInActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class SignOutFragment extends Fragment {
    private GoogleSignInClient googleSignInClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Activity context = (Activity) view.getContext();

        googleSignInClient = GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN);

        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            Intent intent = new Intent(context, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        return view;
    }
}
