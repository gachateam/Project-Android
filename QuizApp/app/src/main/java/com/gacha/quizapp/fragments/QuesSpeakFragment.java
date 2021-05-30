package com.gacha.quizapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesSpeak;
import com.gacha.quizapp.R;

public class QuesSpeakFragment extends AbstractFragment {
    private QuesSpeak quesSpeak;

    @Override
    public void setQuestion(Ques question) {
        this.quesSpeak = (QuesSpeak) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_speak_fragment,container,false);



        return view;
    }
}