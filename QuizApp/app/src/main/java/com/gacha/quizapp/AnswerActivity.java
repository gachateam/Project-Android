package com.gacha.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesListen;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.QuesMulImage;
import com.gacha.quizapp.Model.QuesSpeak;
import com.gacha.quizapp.fragments.AbstractFragment;
import com.gacha.quizapp.fragments.QuesListenFragment;
import com.gacha.quizapp.fragments.QuesMulFragment;
import com.gacha.quizapp.fragments.QuesMulImageFragment;
import com.gacha.quizapp.fragments.QuesSpeakFragment;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageButton imageButton;
    private int questionID = 0;
    private FragmentTransaction fragmentTransaction;
    private AbstractFragment fragment;
    private ArrayList<Ques> listQues;

    private static final String TAG = AnswerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);

        Intent intent = getIntent();
        listQues = (ArrayList<Ques>) intent.getExtras().get("ques");

        updateUI();

        //get ui
        buttonNext = findViewById(R.id.btnNext);
        imageButton = findViewById(R.id.btn_prev);

        //set onclick button back screen
        imageButton.setOnClickListener(v -> {
            finish();
        });

        //set onclick next question
        buttonNext.setOnClickListener(v -> {
            questionID++;
            if (questionID < listQues.size()) {
                updateUI();
            }
            if (questionID > listQues.size() - 2) {
                ((Button) v).setText("Submit");
                ((Button) v).setOnClickListener(showResult);
            }
        });


        //create check box group
//        cbs = new ArrayList<>();
//        cbs.add(findViewById(R.id.checkA));
//        cbs.add(findViewById(R.id.checkB));
//        cbs.add(findViewById(R.id.checkC));
//        cbs.add(findViewById(R.id.checkD));
//        checkBoxGroup = new CheckBoxGroup(cbs.get(0), cbs.get(1), cbs.get(2), cbs.get(3));
//        //get view
//        textView = findViewById(R.id.question);
//        buttonNext = findViewById(R.id.btnNext);
//        imageButton = findViewById(R.id.btn_prev);
//        //get Intent
//        Intent intent = getIntent();
//        quesMul = (ArrayList<QuesMul>) intent.getExtras().get("ques");
//
//        buttonNext.setOnClickListener(quesNext);
//        imageButton.setOnClickListener((v)->{
//            finish();
//        });
    }

    private void updateUI() {
        Fragment f = getSupportFragmentManager().findFragmentByTag(questionID + "");
        if (listQues.get(questionID) instanceof QuesListen) {
            fragment = f != null ? (QuesListenFragment) f : new QuesListenFragment();
        } else if (listQues.get(questionID) instanceof QuesMul) {
            fragment = f != null ? (QuesMulFragment) f : new QuesMulFragment();
        } else if (listQues.get(questionID) instanceof QuesMulImage) {
            fragment = f != null ? (QuesMulImageFragment) f : new QuesMulImageFragment();
        } else if (listQues.get(questionID) instanceof QuesSpeak) {
            fragment = f != null ? (QuesSpeakFragment) f : new QuesSpeakFragment();
        }
        fragment.setQuestion(listQues.get(questionID));
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ques_fragment, fragment, questionID + "");
        if (getSupportFragmentManager().findFragmentByTag(questionID + "") == null) {
            fragmentTransaction.addToBackStack(questionID + "");
        }
        fragmentTransaction.commit();
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
