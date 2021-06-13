package com.gacha.quizapp.Model;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gacha.quizapp.adapters.RecentQuizAdapter;
import com.gacha.quizapp.ui.home.RecentFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class UserHistory {
    private String uid;
    private FirebaseFirestore firebaseFirestore;
    private String collectionPath;
    DocumentReference documentReference;

    private static final String TAG = UserHistory.class.getSimpleName();

    public String getCollectionPath() {
        return collectionPath;
    }

    public String getUid() {
        return uid;
    }

    public UserHistory() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        uid = firebaseUser.getUid();
        collectionPath = "users/" + uid + "/recent";
    }

    public void getHistoryPoint(int unitId, View view) {
        documentReference = firebaseFirestore.collection(collectionPath).document(unitId + "");
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                Log.d(TAG, "onBindViewHolder: " + document.exists());
                if (document.exists()) {
                    String point = document.get("point").toString() + "%";
                    ((TextView) view).setText(point);
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }

    public void getListUnit(ArrayList<Unit> units, RecentQuizAdapter adapterRecentQuiz) {
        firebaseFirestore.collection(collectionPath).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                adapterRecentQuiz.setList(Unit.getAllQuesHistoryInList(units, task));
                adapterRecentQuiz.notifyDataSetChanged();
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }
}
