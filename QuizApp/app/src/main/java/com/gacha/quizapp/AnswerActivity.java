package com.gacha.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gacha.quizapp.Model.Quiz;
import com.gacha.quizapp.Model.Unit;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private Quiz quiz;
    private ArrayList<CheckBox> cbs;
    private Button button;
    private Unit unit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);

        cbs = new ArrayList<>();

        cbs.add(findViewById(R.id.checkA));
        cbs.add(findViewById(R.id.checkB));
        cbs.add(findViewById(R.id.checkC));
        cbs.add(findViewById(R.id.checkD));

        button = findViewById(R.id.btnNext);

        new CheckBoxGroup(cbs.get(0),cbs.get(1),cbs.get(2),cbs.get(3));

        button.setOnClickListener(showResult);

    }
    private void setQuiz(){
        for (int i = 0; i < cbs.size(); i++) {
            cbs.get(i).setText(quiz.getAns().get(i));
        }
    }
    private final View.OnClickListener quesNext = v->{

    };
    private final View.OnClickListener showResult = v -> {
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
