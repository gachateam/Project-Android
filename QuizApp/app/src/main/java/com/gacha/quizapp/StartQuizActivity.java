package com.gacha.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.Unit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StartQuizActivity extends AppCompatActivity {

    private static final String TAG = StartQuizActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_quiz_screen);

        Intent intent = getIntent();

        TextView name = findViewById(R.id.quizName);
        TextView description = findViewById(R.id.quizDescription);
        Button button = findViewById(R.id.btn_start_quiz);

        String unitName = intent.getExtras().get("name").toString();
        String unitDescription = intent.getExtras().get("description").toString();
        ArrayList<Ques> quesMul = (ArrayList<Ques>) intent.getExtras().get("ques");

        Log.d(TAG, "onCreate: "+quesMul.size());

        name.setText(unitName);
        description.setText(unitDescription);

        Unit unit = new Unit();

        button.setOnClickListener(v -> {
            Intent intent1 = new Intent(StartQuizActivity.this, AnswerActivity.class);
            intent1.putExtra("ques", quesMul);
            intent1.putExtra("unitId", intent.getExtras().get("unitId").toString());
            StartQuizActivity.this.startActivity(intent1);
            finish();
        });
    }
}
