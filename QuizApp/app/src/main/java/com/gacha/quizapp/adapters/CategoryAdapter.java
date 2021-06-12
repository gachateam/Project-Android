package com.gacha.quizapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.AnswerActivity;
import com.gacha.quizapp.Model.Category;
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

    private static final String TAG = CategoryAdapter.class.getSimpleName();

    public CategoryAdapter(Activity context, int layoutID, ArrayList<Category> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
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
        ArrayList<Integer> color = new ArrayList<>();
        color.add(context.getResources().getColor(R.color.black));
        color.add(context.getResources().getColor(R.color.blue));
        color.add(context.getResources().getColor(R.color.colorPrimary));
        color.add(context.getResources().getColor(R.color.colorPrimaryDark));
        holder.constraintLayout.setBackgroundColor(color.get((new Random()).nextInt(4)));
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.category_item_recycler_view);
            category = itemView.findViewById(R.id.category);
        }
    }
}
