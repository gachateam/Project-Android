package com.gacha.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.RecentQuiz;
import com.gacha.quizapp.adapters.CategoryAdapter;
import com.gacha.quizapp.adapters.RecentQuizAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
//
//        Button btn = findViewById(R.id.btnNext);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

        RecyclerView recyclerViewCategory = findViewById(R.id.category_recycler_view);
        RecyclerView recyclerViewRecentQuiz = findViewById(R.id.recent_quiz_recycler_view);

        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<RecentQuiz> listRecentQuiz = new ArrayList<>();

        listCategory.add(new Category("money",this.getResources().getColor(R.color.black)));
        listCategory.add(new Category("category",this.getResources().getColor(R.color.blue)));
        listCategory.add(new Category("test",this.getResources().getColor(R.color.colorPrimary)));
        listCategory.add(new Category("clone",this.getResources().getColor(R.color.colorPrimaryDark)));

        listRecentQuiz.add(new RecentQuiz("131","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));
        listRecentQuiz.add(new RecentQuiz("131","123"));

        CategoryAdapter adapterCategory = new CategoryAdapter(this,R.layout.category_item_recycler_view, listCategory);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(this,1,LinearLayoutManager.HORIZONTAL,false);

        RecentQuizAdapter adapterRecentQuiz = new RecentQuizAdapter(this,R.layout.recent_quiz_recycler_view_item, listRecentQuiz);
        GridLayoutManager gridLayoutManagerRecentQuiz = new GridLayoutManager(this,1,LinearLayoutManager.VERTICAL,false);

        recyclerViewCategory.setLayoutManager(gridLayoutManagerCategory);
        recyclerViewCategory.setAdapter(adapterCategory);

        recyclerViewRecentQuiz.setLayoutManager(gridLayoutManagerRecentQuiz);
        recyclerViewRecentQuiz.setAdapter(adapterRecentQuiz);

    }


}