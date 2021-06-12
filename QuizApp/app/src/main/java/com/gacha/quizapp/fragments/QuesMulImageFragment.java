package com.gacha.quizapp.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.gacha.quizapp.AnswerActivity;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMulImage;
import com.gacha.quizapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class QuesMulImageFragment extends AbstractFragment {
    private QuesMulImage quesMulImage;
    private ArrayList<ConstraintLayout> ques;

    private static final String TAG = QuesMulImageFragment.class.getSimpleName();

    @Override
    public void setQuestion(Ques question) {
        this.quesMulImage = (QuesMulImage) question;
    }

    @Override
    public Ques updateUserInteraction(ArrayList<Ques> listQues, int questionID) {
        QuesMulImage quesMulImage =(QuesMulImage) listQues.get(questionID);

        for (int i = 0; i < ques.size(); i++) {
            if (ques.get(i).isSelected()){
                quesMulImage.setUserAns(i);
            }
        }

        return quesMulImage;
    }

    View.OnClickListener onClickListener = v -> {
        v.setSelected(!v.isSelected());
        if (v.isSelected()){
            int count = ques.size();
            for (int i = 0; i < count; i++) {
                if (ques.get(i).getId() != v.getId()) {
                    ques.get(i).setSelected(false);
                }
            }
        }
        Log.d(TAG, "select: "+v.isSelected());
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_mul_image_fragment, container, false);

        TextView textView = view.findViewById(R.id.ques);
        textView.setText(quesMulImage.getQues());
        ConstraintLayout ques1 = view.findViewById(R.id.quesImage1);
        ConstraintLayout ques2 = view.findViewById(R.id.quesImage4);
        ConstraintLayout ques3 = view.findViewById(R.id.quesImage2);
        ConstraintLayout ques4 = view.findViewById(R.id.quesImage3);
        ques = new ArrayList<>();
        Collections.addAll(ques, ques1, ques2, ques3, ques4);
        for (ConstraintLayout constrain : ques) {
            constrain.setOnClickListener(onClickListener);
        }

        ImageView imageView1 = view.findViewById(R.id.imageQues1);
        ImageView imageView2 = view.findViewById(R.id.imageQues2);
        ImageView imageView3 = view.findViewById(R.id.imageQues3);
        ImageView imageView4 = view.findViewById(R.id.imageQues4);
        TextView textImage1 = view.findViewById(R.id.textQues1);
        TextView textImage2 = view.findViewById(R.id.textQues2);
        TextView textImage3 = view.findViewById(R.id.textQues3);
        TextView textImage4 = view.findViewById(R.id.textQues4);
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ArrayList<TextView> textViews = new ArrayList<>();
        Collections.addAll(imageViews, imageView1, imageView2, imageView3, imageView4);
        Collections.addAll(textViews, textImage1, textImage2, textImage3, textImage4);

        for (int i = 0; i < imageViews.size(); i++) {
            Glide.with(QuesMulImageFragment.this).load(this.quesMulImage.getAns().get(i).image).into(imageViews.get(i));
            textViews.get(i).setText(this.quesMulImage.getAns().get(i).ans);
        }

        return view;
    }
}
