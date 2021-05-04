package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
// Initialize
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);

    }
    public  void showPopup(View v){
        TextView txtclose;
        Button btnOk ;
     //   myDialog.setContentView(R.id.);
        txtclose = (TextView) myDialog.findViewById(R.id.close);
        btnOk = (Button) myDialog.findViewById(R.id.ok);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myDialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myDialog.show();
    }



}