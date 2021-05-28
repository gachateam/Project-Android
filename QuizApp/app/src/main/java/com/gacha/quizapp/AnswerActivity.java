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

import com.gacha.quizapp.Model.QuesMul;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private ArrayList<QuesMul> quesMul;
    private Button buttonNext;
    private ArrayList<CheckBox> cbs;
    private TextView textView;
    private ImageButton imageButton;
    private int pos = 0;
    private CheckBoxGroup checkBoxGroup;

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
        checkBoxGroup = new CheckBoxGroup(cbs.get(0), cbs.get(1), cbs.get(2), cbs.get(3));
        //get view
        textView = findViewById(R.id.question);
        buttonNext = findViewById(R.id.btnNext);
        imageButton = findViewById(R.id.btn_prev);
        //get Intent
        Intent intent = getIntent();
        quesMul = (ArrayList<QuesMul>) intent.getExtras().get("ques");

        buttonNext.setOnClickListener(quesNext);
        imageButton.setOnClickListener((v)->{
            finish();
        });
    }

    private final View.OnClickListener quesNext = v -> {
        if ((pos + 2) == quesMul.size()) {
            String submit = "submit";
            buttonNext.setText(submit);
            check();
            return;
        }
        showAns(quesMul.get(++pos));
        checkBoxGroup.setDefault();
    };

    public void check() {
        Log.d(TAG, "check: " + (pos+2));
        if ((pos + 2) == quesMul.size()) {
            buttonNext.setOnClickListener(showResult);
        }
    }

    private void showAns(QuesMul quesMul) {
        textView.setText(quesMul.getQues());
        for (int i = 0; i < cbs.size(); i++) {
            cbs.get(i).setText(quesMul.getAns().get(i));
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
