package com.gacha.quizapp;

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
    private ArrayList<QuesMul> quesMul;
    private Button buttonNext;
    private ArrayList<CheckBox> cbs;
    private TextView textView;
    private ImageButton imageButton;
    private int questionID = 2;
    private CheckBoxGroup checkBoxGroup;
    private FragmentTransaction fragmentTransaction;
    private AbstractFragment fragment;
    private ArrayList<Ques> listQues;

    private static final String TAG = AnswerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);

        ArrayList<String> ansC = new ArrayList<>();
        ansC.add("123");
        ansC.add("123");
        ansC.add("123");
        ansC.add("123");
        QuesMul quesMul = new QuesMul(1, 2, 1, "test", 1, ansC);
        QuesMulImage quesMulImage = new QuesMulImage();
        QuesSpeak quesSpeak = new QuesSpeak();
        QuesListen quesListen = new QuesListen();

        listQues = new ArrayList<>();

        listQues.add(quesMul);
        listQues.add(quesMulImage);
        listQues.add(quesListen);
        listQues.add(quesSpeak);

        updateUI();

//        Fragment f = getSupportFragmentManager().findFragmentByTag(1 + "");
//        fragment = f != null ? (QuesMulFragment) f : new QuesMulFragment();
//        fragment.setQuestion(quesMul);
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.ques_fragment, fragment, 1 + "");
////        fragmentTransaction.addToBackStack(1 + "");
//        fragmentTransaction.commit();


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
        Fragment f = getSupportFragmentManager().findFragmentByTag(1 + "");
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

    private final View.OnClickListener quesNext = v -> {
        if ((questionID + 2) == quesMul.size()) {
            String submit = "submit";
            buttonNext.setText(submit);
            check();
            return;
        }
        showAns(quesMul.get(++questionID));
        checkBoxGroup.setDefault();
    };

    public void check() {
        Log.d(TAG, "check: " + (questionID + 2));
        if ((questionID + 2) == quesMul.size()) {
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
