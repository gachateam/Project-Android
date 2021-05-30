package com.gacha.quizapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuesMulFragment extends AbstractFragment {
    private QuesMul quesMul;
    private ArrayList<CheckBox> cbs;

    public QuesMulFragment() {
        this.cbs = new ArrayList<>();
    }

    @Override
    public void setQuestion(Ques question) {
        this.quesMul = (QuesMul) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_mul_fragment, container, false);

        TextView ques = view.findViewById(R.id.question);
        CheckBox cb1 = view.findViewById(R.id.checkA);
        CheckBox cb2 = view.findViewById(R.id.checkB);
        CheckBox cb3 = view.findViewById(R.id.checkC);
        CheckBox cb4 = view.findViewById(R.id.checkD);
        Collections.addAll(this.cbs, cb1, cb2, cb3, cb4);

        ques.setText(this.quesMul.getQues());
        for (int i = 0; i < cbs.size();i++){
            cbs.get(i).setText(this.quesMul.getAns().get(i));
            Log.d("TAG", "onCreateView: "+this.quesMul.getAns().get(i));
        }

        return view;
    }
}
