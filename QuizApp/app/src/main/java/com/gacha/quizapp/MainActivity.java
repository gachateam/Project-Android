package com.gacha.quizapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);
        ActionBar actionBar = getSupportActionBar();

        Button btn = findViewById(R.id.btnNext);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        btn.setOnClickListener(v -> myRef.setValue("lo cc"));

        myDialog = new Dialog(this);
    }

    public void showPopup(View v){
        TextView txtClose;
        Button btnOk ;
        //   myDialog.setContentView(R.id.);
        txtClose = (TextView) myDialog.findViewById(R.id.close);
        btnOk = (Button) myDialog.findViewById(R.id.ok);
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
    }
}