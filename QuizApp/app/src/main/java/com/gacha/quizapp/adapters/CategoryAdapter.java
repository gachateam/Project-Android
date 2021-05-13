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

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Activity context;
    private int layoutID;
    private ArrayList<Category> list;

    public CategoryAdapter(Activity context, int layoutID, ArrayList<Category> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(layoutID,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.category.setText(list.get(position).getCategory());
        holder.constraintLayout.setBackgroundColor(list.get(position).getColor());

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
