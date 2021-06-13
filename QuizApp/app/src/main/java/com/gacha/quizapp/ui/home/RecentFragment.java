package com.gacha.quizapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.Model.UserHistory;
import com.gacha.quizapp.R;
import com.gacha.quizapp.adapters.RecentQuizAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RecentFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_screen, container, false);
        Activity context = (Activity) view.getContext();

        Intent intent = context.getIntent();
        ArrayList<Ques> listQues = (ArrayList<Ques>) intent.getExtras().get("listQues");
        ArrayList<Unit> listUnit = (ArrayList<Unit>) intent.getExtras().get("listUnit");

        RecyclerView recyclerViewRecentQuiz = view.findViewById(R.id.recent_quiz_recycler_view);
        GridLayoutManager gridLayoutManagerRecentQuiz = new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false);
        recyclerViewRecentQuiz.setLayoutManager(gridLayoutManagerRecentQuiz);
        RecentQuizAdapter adapterRecentQuiz = new RecentQuizAdapter(context, R.layout.recent_quiz_recycler_view_item, new ArrayList<>(), listQues);

        UserHistory userHistory = new UserHistory();
        userHistory.getListUnit(listUnit, adapterRecentQuiz);

        recyclerViewRecentQuiz.setAdapter(adapterRecentQuiz);

        return view;
    }
}