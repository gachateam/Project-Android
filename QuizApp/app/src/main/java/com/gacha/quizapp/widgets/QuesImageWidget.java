package com.gacha.quizapp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gacha.quizapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuesImageWidget extends LinearLayout {
    private ConstraintLayout ques1 , ques2, ques3, ques4;
    private ArrayList<ConstraintLayout> ques = new ArrayList<>();
    private ViewGroup playerLayout;

    public QuesImageWidget(@NonNull Context context) {
        super(context);
        init();
    }

    public QuesImageWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuesImageWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.ques_image_widget,this);
        playerLayout = (ViewGroup) getChildAt(0);
        ques1 = playerLayout.findViewById(R.id.quesImage1);
        ques2 = playerLayout.findViewById(R.id.quesImage2);
        ques3 = playerLayout.findViewById(R.id.quesImage3);
        ques4 = playerLayout.findViewById(R.id.quesImage4);
    }
}
