package com.gacha.quizapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMulImage;
import com.gacha.quizapp.R;

public class QuesMulImageFragment extends AbstractFragment {
    private QuesMulImage quesMulImage;

    @Override
    public void setQuestion(Ques question) {
        this.quesMulImage = (QuesMulImage) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_mul_image_fragment,container,false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
