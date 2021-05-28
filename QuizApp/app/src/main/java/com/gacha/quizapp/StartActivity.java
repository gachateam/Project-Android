package com.gacha.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.QuesRead;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

//        HashMap<String,Integer> color = new HashMap<>();
//        color.put("black",this.getResources().getColor(R.color.black));
//        color.put("blue",this.getResources().getColor(R.color.blue));
//        color.put("colorPrimary",this.getResources().getColor(R.color.colorPrimary));
//        color.put("colorPrimaryDark",this.getResources().getColor(R.color.colorPrimaryDark));

        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<Ques> listQues = new ArrayList<>();
        ArrayList<Unit> listUnit = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.child("category").getChildren()) {
                    listCategory.add(data.getValue(Category.class));
                }
                for (DataSnapshot data : snapshot.child("ques").getChildren()) {
                    int type;
                    if ((type = data.child("type").getValue(Integer.class)) == Ques.TYPE_MUL_QUES) {
                        listQues.add(data.getValue(QuesMul.class));
                    }else if(type == Ques.TYPE_READ){
                        listQues.add(data.getValue(QuesRead.class));
                    }else if (type == Ques.TYPE_SPEAK){
                        listQues.add(data.getValue(QuesSpeak.class));
                    }
                }
                for (DataSnapshot data : snapshot.child("unit").getChildren()) {
                    listUnit.add(data.getValue(Unit.class));
                }

                for (Ques ques : listQues) {
                    Log.d(TAG, "onDataChange: " + ques);
                }
//                Intent intent = new Intent(StartActivity.this,NavigationActivity.class);
//                intent.putExtra("listCategory",listCategory);
//                intent.putExtra("listQues",listQues);
//                intent.putExtra("listUnit",listUnit);
//                StartActivity.this.startActivity(intent);
//                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }
}