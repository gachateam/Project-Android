package com.gacha.quizapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class TestActivity extends Activity {
    private ConstraintLayout ques1 , ques2, ques3, ques4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_mul_image_fragment);
    }
}
