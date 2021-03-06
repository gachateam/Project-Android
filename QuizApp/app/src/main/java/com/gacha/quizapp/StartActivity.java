package com.gacha.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.QuesListen;
import com.gacha.quizapp.Model.QuesMulImage;
import com.gacha.quizapp.Model.QuesSpeak;
import com.gacha.quizapp.Model.Unit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = StartActivity.class.getSimpleName();
    ArrayList<Category> listCategory;
    ArrayList<Ques> listQues;
    ArrayList<Unit> listUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        listCategory = new ArrayList<>();
        listQues = new ArrayList<>();
        listUnit = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Category category;
                for (DataSnapshot data : snapshot.child("category").getChildren()) {
                    category = data.getValue(Category.class);
                    category.setId(Integer.parseInt(data.getKey()));
                    listCategory.add(category);
                }
                for (DataSnapshot data : snapshot.child("ques").getChildren()) {
                    int type;
                    if ((type = data.child("type").getValue(Integer.class)) == Ques.TYPE_MUL_QUES) {
                        listQues.add(data.getValue(QuesMul.class));
                    } else if (type == Ques.TYPE_LISTEN) {
                        listQues.add(data.getValue(QuesListen.class));
                    } else if (type == Ques.TYPE_SPEAK) {
                        listQues.add(data.getValue(QuesSpeak.class));
                    } else if (type == Ques.TYPE_MUL_QUES_IMAGE) {
                        listQues.add(data.getValue(QuesMulImage.class));
                    }
                }
                for (DataSnapshot data : snapshot.child("unit").getChildren()) {
                    listUnit.add(data.getValue(Unit.class));
                }

//                for (Category category1:listCategory) {
//                    Log.d(TAG, "onDataChange: "+category1);
//                }

                Intent intent = new Intent(StartActivity.this, NavigationActivity.class);
                intent.putExtra("listCategory", listCategory);
                intent.putExtra("listQues", listQues);
                intent.putExtra("listUnit", listUnit);
                StartActivity.this.startActivity(intent);
                ref.removeEventListener(this);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }
}