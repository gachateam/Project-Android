package com.gacha.quizapp;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnswerActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageButton imageButton;
    private int questionID = 0;
    private FragmentTransaction fragmentTransaction;
    private AbstractFragment fragment;
    private ArrayList<Ques> listQues;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private FirebaseFirestore firebaseFirestore;
    private String unitId;

    private static final String TAG = AnswerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userID = firebaseUser.getUid();

        Intent intent = getIntent();
        listQues = (ArrayList<Ques>) intent.getExtras().get("ques");
        unitId = intent.getExtras().getString("unitId");

        updateUI();

        //get ui
        buttonNext = findViewById(R.id.btnNext);
        imageButton = findViewById(R.id.btn_prev);

        //set onclick button back screen
        imageButton.setOnClickListener(v -> finish());

        //set onclick next question
        buttonNext.setOnClickListener(v -> {
            listQues.set(questionID, fragment.updateUserInteraction(listQues, questionID));
            questionID++;
            if (questionID < listQues.size()) {
                updateUI();
            }
            if (questionID > listQues.size() - 2) {
                String submit = "Submit";
                ((Button) v).setText(submit);
                v.setOnClickListener(showResult);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void updateUI() {
        TextView textView = findViewById(R.id.questionNumber);
        textView.setText(String.format("question: %d", 1 + questionID));
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

    public View.OnClickListener showResult = this::onClick;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void onClick(View v) {
        listQues.set(questionID, fragment.updateUserInteraction(listQues, questionID));
        AlertDialog.Builder builder = new AlertDialog.Builder(AnswerActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom, viewGroup, false);
        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView textView = alertDialog.findViewById(R.id.result);
        Log.d(TAG, "onClick: size: " + listQues.size());
        //cal point
        double point = 0;
        for (Ques ques : listQues) {
            point = ques.getPoint() ? point + 1 : point;
            Log.d(TAG, "onClick: ques: " + ques.getPoint() + " " + ques.getType());
        }
        double result = (point / listQues.size()) * 100;
        String resultString = result + "%";

        assert textView != null;
        textView.setText(resultString);
        Button btnOk = alertDialog.findViewById(R.id.btn_ok);

        assert btnOk != null;
        btnOk.setOnClickListener(v1 -> {

            String collectionPath = "users/" + userID + "/recent";
            DocumentReference documentReference = firebaseFirestore.collection(collectionPath).document(unitId);
            Map<String, Object> pointMap = new HashMap<>();
            pointMap.put("point", result);
            pointMap.put("numberQues", listQues.size());
            ArrayList<Boolean> quesResult = new ArrayList<>();
            for (Ques ques : listQues) {
                quesResult.add(ques.getPoint());
            }
            pointMap.put("quesResult", quesResult);
            documentReference.set(pointMap);

            AnswerActivity.this.finish();
            alertDialog.dismiss();
        });
    }
}
