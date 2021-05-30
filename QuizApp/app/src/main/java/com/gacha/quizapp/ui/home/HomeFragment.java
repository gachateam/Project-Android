package com.gacha.quizapp.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartQuizActivity;
import com.gacha.quizapp.adapters.CategoryAdapter;
import com.gacha.quizapp.adapters.RecentQuizAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Activity context = (Activity) view.getContext();

        Intent intent = context.getIntent();
        ArrayList<Category> listCategory = (ArrayList<Category>) intent.getExtras().get("listCategory");
        ArrayList<Ques> listQues = (ArrayList<Ques>) intent.getExtras().get("listQues");
        ArrayList<Unit> listUnit = (ArrayList<Unit>) intent.getExtras().get("listUnit");

        for(Ques ques: listQues){
            Log.d(TAG, "onCreateView: "+ques);
        }

        RecyclerView recyclerViewCategory = view.findViewById(R.id.category_recycler_view);
        RecyclerView recyclerViewRecentQuiz = view.findViewById(R.id.recent_quiz_recycler_view);

        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManagerRecentQuiz = new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false);

        recyclerViewCategory.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewRecentQuiz.setLayoutManager(gridLayoutManagerRecentQuiz);

        CategoryAdapter adapterCategory = new CategoryAdapter(context, R.layout.category_item_recycler_view, listCategory);
        RecentQuizAdapter adapterRecentQuiz = new RecentQuizAdapter(context, R.layout.recent_quiz_recycler_view_item, listUnit, listQues);

        recyclerViewRecentQuiz.setAdapter(adapterRecentQuiz);
        recyclerViewCategory.setAdapter(adapterCategory);

        return view;
    }
}