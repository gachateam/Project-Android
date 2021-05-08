package com.gacha.quizapp.ui.home;

import android.app.Activity;
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
import com.gacha.quizapp.Model.RecentQuizzes;
import com.gacha.quizapp.R;
import com.gacha.quizapp.adapters.CategoryAdapter;
import com.gacha.quizapp.adapters.RecentQuizAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        Activity context = (Activity) view.getContext();

        //create color array
        HashMap<String,Integer> color = new HashMap<>();
        color.put("black",this.getResources().getColor(R.color.black));
        color.put("blue",this.getResources().getColor(R.color.blue));
        color.put("colorPrimary",this.getResources().getColor(R.color.colorPrimary));
        color.put("colorPrimaryDark",this.getResources().getColor(R.color.colorPrimaryDark));

        //get recycler view from view
        RecyclerView recyclerViewCategory = view.findViewById(R.id.category_recycler_view);
        RecyclerView recyclerViewRecentQuiz = view.findViewById(R.id.recent_quiz_recycler_view);

        //construct list category and list recent quiz
        ArrayList<Category> listCategory = new ArrayList<>();
        ArrayList<RecentQuizzes> listRecentQuizzes = new ArrayList<>();

        //create category manager
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(context,1, LinearLayoutManager.HORIZONTAL,false);

        //create recent quiz manager
        GridLayoutManager gridLayoutManagerRecentQuiz = new GridLayoutManager(context,1,LinearLayoutManager.VERTICAL,false);

        //set manager to recycler view category
        recyclerViewCategory.setLayoutManager(gridLayoutManagerCategory);

        //set manager to recycler view recent quiz
        recyclerViewRecentQuiz.setLayoutManager(gridLayoutManagerRecentQuiz);

        //get firebase reference
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("unit");

        Query query = ref.limitToFirst(5);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Category> categories = new ArrayList<>();
                ArrayList<RecentQuizzes> recentQuizzes = new ArrayList<>();
                for (DataSnapshot data: snapshot.getChildren()){
                    String name = data.child("name").getValue(String.class);
                    String description = data.child("description").getValue(String.class);
                    int colorValue = color.get(data.child("color").getValue(String.class));
                    categories.add(new Category(name,colorValue));
                    recentQuizzes.add(new RecentQuizzes(name,description));
                }
                listCategory.clear();
                listRecentQuizzes.clear();
                listCategory.addAll(categories);
                listRecentQuizzes.addAll(recentQuizzes);
                CategoryAdapter adapterCategory = new CategoryAdapter(context,R.layout.category_item_recycler_view, listCategory);
                RecentQuizAdapter adapterRecentQuiz = new RecentQuizAdapter(context,R.layout.recent_quiz_recycler_view_item, listRecentQuizzes);
                recyclerViewRecentQuiz.setAdapter(adapterRecentQuiz);
                recyclerViewCategory.setAdapter(adapterCategory);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: "+error.getMessage());
            }
        });

        return view;
    }
}