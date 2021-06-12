package com.gacha.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gacha.quizapp.adapters.SignInAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private final int RC_SIGN_IN = 1;
    private CallbackManager callbackManager;
    private String userID;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager2);
        Button btnFb = findViewById(R.id.button4);
        Button btnGg = findViewById(R.id.button5);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        tabLayout.addTab(tabLayout.newTab().setText("Sign In"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final SignInAdapter adapter = new SignInAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGg.setOnClickListener(v -> signIn());

        callbackManager = CallbackManager.Factory.create();
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("test", "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d("test", "facebook:onCancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("test", "facebook:onError" + error.getMessage());
                    }
                });
            }
        });



    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount acc = task.getResult(ApiException.class);
                Toast.makeText(SignInActivity.this, "Sign In Successfully", Toast.LENGTH_LONG).show();
                firebaseGoogleAuth(acc.getIdToken());
            } catch (ApiException e){
                Toast.makeText(SignInActivity.this, "Error" + e.getMessage() ,Toast.LENGTH_LONG).show();
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseGoogleAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SignInActivity.this);
//                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("userName", acct.getDisplayName());
                            user.put("email", acct.getEmail());
                            documentReference.set(user);
                            startActivity(new Intent(SignInActivity.this, StartActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignInActivity.this, "Errorx2", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("test", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("test", "signInWithCredential:success");
                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                            Profile profile = Profile.getCurrentProfile();
//                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("userName", profile.getName());
                            user.put("email", firebaseUser.getEmail());
                            documentReference.set(user);
                            startActivity(new Intent(SignInActivity.this, StartActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("test", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
