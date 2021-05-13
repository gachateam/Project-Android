package com.gacha.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gacha.quizapp.Model.Quiz;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.ui.home.HomeFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
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

        String unitName = intent.getExtras().get("quizName").toString();
        String unitDescription = intent.getExtras().get("quizDescription").toString();
        name.setText(unitName);
        description.setText(unitDescription);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("TracNghiem");

        Unit unit = new Unit();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.child("name").getValue(String.class).equals(unitName)){
                        unit.setUnitName(unitName);
                        unit.setUnitDescription(unitDescription);
                        ArrayList<Quiz> quizzes = new ArrayList<>();
                        for (DataSnapshot dataQuiz : data.child("cauhoi").getChildren()) {
                            quizzes.add(dataQuiz.getValue(Quiz.class));
                        }
                        unit.setQuizList(quizzes);
                    }
                }
                button.setOnClickListener(v->{
                    Intent intent1 = new Intent(StartQuizActivity.this ,AnswerActivity.class);
                    intent1.putExtra("unit" , unit);
                    StartQuizActivity.this.startActivity(intent1);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
