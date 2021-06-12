package com.gacha.quizapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.AnswerActivity;
import com.gacha.quizapp.Model.Category;
import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartActivity;
import com.gacha.quizapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Activity context;
    private int layoutID;
    private ArrayList<Category> list;
    private ArrayList<Unit> units;
    private ArrayList<Ques> ques;
    private RecentQuizAdapter adapterRecentQuiz;
    private ArrayList<Integer> color;

    private static final String TAG = CategoryAdapter.class.getSimpleName();

    public CategoryAdapter(Activity context, int layoutID, ArrayList<Category> list, ArrayList<Unit> units, ArrayList<Ques> ques, RecentQuizAdapter adapterRecentQuiz) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
        this.units = units;
        this.ques = ques;
        this.adapterRecentQuiz = adapterRecentQuiz;
        color = new ArrayList<>();
        color.add(context.getResources().getColor(R.color.black));
        color.add(context.getResources().getColor(R.color.blue));
        color.add(context.getResources().getColor(R.color.colorPrimary));
        color.add(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(layoutID, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category.setText(list.get(position).getCategory());
        holder.constraintLayout.setBackgroundColor(color.get((new Random()).nextInt(4)));
        holder.catItem.setOnClickListener(v -> {
            TextView cat = context.findViewById(R.id.cat);
            cat.setText(list.get(position).getCategory());
            adapterRecentQuiz.setList(Unit.getAllQuesByCategoryInList(units,list.get(position).getId()));
            adapterRecentQuiz.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView category;
        private ConstraintLayout constraintLayout;
        private CardView catItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.category_item_recycler_view);
            category = itemView.findViewById(R.id.category);
            catItem = itemView.findViewById(R.id.catItem);
        }
    }
}
