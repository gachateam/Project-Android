package com.gacha.quizapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TestActivity extends Activity {
    private ConstraintLayout ques1 , ques2, ques3, ques4;

    private static final String TAG = StartActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_mul_image_fragment);

        ImageView i1 = findViewById(R.id.imageQues1);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child("quesImage/dog.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("TAG", "onSuccess: "+uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onFailure: "+e.getMessage());
            }
        });
//        Glide.with(TestActivity.this).load("https://firebasestorage.googleapis.com/v0/b/phonestore-ceaf4.appspot.com/o/dog.jpg?alt=media&token=3adf8598-6099-4470-87f2-50dc6ba080b5").into(i1);

    }
}
