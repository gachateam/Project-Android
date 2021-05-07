package com.gacha.quizapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.RecentQuiz;
import com.gacha.quizapp.R;
import com.gacha.quizapp.adapters.CategoryAdapter;
import com.gacha.quizapp.adapters.RecentQuizAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Activity context = (Activity) view.getContext();

        RecyclerView recyclerViewCategory = view.findViewById(R.id.category_recycler_view);
        RecyclerView recyclerViewRecentQuiz = view.findViewById(R.id.recent_quiz_recycler_view);

        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<RecentQuiz> listRecentQuiz = new ArrayList<>();

        listCategory.add(new Category("money",this.getResources().getColor(R.color.black)));
        listCategory.add(new Category("category",this.getResources().getColor(R.color.blue)));
        listCategory.add(new Category("test",this.getResources().getColor(R.color.colorPrimary)));
        listCategory.add(new Category("clone",this.getResources().getColor(R.color.colorPrimaryDark)));

        listRecentQuiz.add(new RecentQuiz("13123123123123123121","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));

        CategoryAdapter adapterCategory = new CategoryAdapter(context,R.layout.category_item_recycler_view, listCategory);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(context,1, LinearLayoutManager.HORIZONTAL,false);

        RecentQuizAdapter adapterRecentQuiz = new RecentQuizAdapter(context,R.layout.recent_quiz_recycler_view_item, listRecentQuiz);
        GridLayoutManager gridLayoutManagerRecentQuiz = new GridLayoutManager(context,1,LinearLayoutManager.VERTICAL,false);

        recyclerViewCategory.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategory.setAdapter(adapterCategory);

        recyclerViewRecentQuiz.setLayoutManager(gridLayoutManagerRecentQuiz);
        recyclerViewRecentQuiz.setAdapter(adapterRecentQuiz);

        return view;
    }
}