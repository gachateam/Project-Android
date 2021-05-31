package com.gacha.quizapp.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMulImage;
import com.gacha.quizapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QuesMulImageFragment extends AbstractFragment {
    private QuesMulImage quesMulImage;

    @Override
    public void setQuestion(Ques question) {
        this.quesMulImage = (QuesMulImage) question;
    }

    @Override
    public Ques updateUserInteraction(ArrayList<Ques> listQues, int questionID) {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ques_mul_image_fragment,container,false);

        StorageReference storage = FirebaseStorage.getInstance().getReference().child("dog.jpg");

        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(QuesMulImageFragment.this).load(this.quesMulImage.getAns().get(0).image).into(imageView);

//        StorageReference gsReference = storage.child()//getReferenceFromUrl("gs://phonestore-ceaf4.appspot.com/dog.jpg");
        return view;
    }
}
