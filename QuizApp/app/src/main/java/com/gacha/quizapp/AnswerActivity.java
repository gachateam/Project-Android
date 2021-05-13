package com.gacha.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gacha.quizapp.Model.Quiz;
import com.gacha.quizapp.Model.Unit;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private Quiz quiz;
    private ArrayList<CheckBox> cbs;
    private Button buttonNext;
    private ImageButton buttonPrev;
    private TextView textView;
    private Unit unit;
    private int pos = 0;

    private static final String TAG = AnswerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);

        //create check box group
        cbs = new ArrayList<>();
        cbs.add(findViewById(R.id.checkA));
        cbs.add(findViewById(R.id.checkB));
        cbs.add(findViewById(R.id.checkC));
        cbs.add(findViewById(R.id.checkD));
        new CheckBoxGroup(cbs.get(0),cbs.get(1),cbs.get(2),cbs.get(3));
        //get view
        textView = findViewById(R.id.question);
        buttonNext = findViewById(R.id.btnNext);
        buttonPrev = findViewById(R.id.btn_prev);
        //get Intent
        Intent intent = getIntent();
        unit = (Unit) intent.getExtras().get("unit");

        Log.d(TAG, "onCreate: "+unit.getQuizList().size());

        showAns(unit.getQuizList().get(pos));

        buttonNext.setOnClickListener(quesNext);
        buttonPrev.setOnClickListener(quesPrev);


    }

    private final View.OnClickListener quesNext = v->{
        if ((pos+1)==unit.getQuizList().size()){
            String submit = "submit";
            buttonNext.setText(submit);
            check();
            return;
        }
        pos = pos>unit.getQuizList().size()?0:pos+1;
        if (pos<unit.getQuizList().size()){
            showAns(unit.getQuizList().get(pos));
        }
    };
    private final View.OnClickListener quesPrev = v->{
        if (pos==unit.getQuizList().size()){
            String submit = "submit";
            buttonNext.setText(submit);
            check();
            return;
        }
        pos = pos<unit.getQuizList().size()?unit.getQuizList().size():pos-1;
        showAns(unit.getQuizList().get(pos));
    };

    public void check(){
        Log.d(TAG, "check: "+(pos+1));
        if ((pos+1)==unit.getQuizList().size()){
            buttonNext.setOnClickListener(showResult);
        }
    }

    private void showAns(Quiz quiz){
        textView.setText(quiz.getCauhoi());
        for (int i = 0; i < cbs.size(); i++) {
            cbs.get(i).setText(quiz.getDapan().get(i));
        }
    }
    public View.OnClickListener showResult = v -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(AnswerActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom, viewGroup, false);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button btnOk = alertDialog.findViewById(R.id.btn_ok);

        assert btnOk != null;
        btnOk.setOnClickListener(v1 -> alertDialog.dismiss());
    };
}
