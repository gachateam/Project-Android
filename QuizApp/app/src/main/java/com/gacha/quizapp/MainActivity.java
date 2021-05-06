package com.gacha.quizapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

     Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);
        ActionBar actionBar = getSupportActionBar();

        Button btn = findViewById(R.id.btnNext);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");



        myDialog = new Dialog(this);
    }

    public void showPopup(View v){
        TextView txtClose;
        Button btnOk ;
        ImageView image;
        //   myDialog.setContentView(R.id.);
        txtClose = (TextView) myDialog.findViewById(R.id.close);
        btnOk = (Button) myDialog.findViewById(R.id.ok);
        image = (ImageView) myDialog.findViewById(R.id.hinh);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();

            }
        });
        myDialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();

            }
        });
        myDialog.show();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        image.setAnimation(animation);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                image.setAnimation(animation1);
            }
        });

    }

}