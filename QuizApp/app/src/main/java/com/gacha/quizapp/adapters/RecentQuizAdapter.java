package com.gacha.quizapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.AnswerActivity;
import com.gacha.quizapp.Model.RecentQuizzes;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartQuizActivity;

import java.util.ArrayList;

public class RecentQuizAdapter extends RecyclerView.Adapter<RecentQuizAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<RecentQuizzes> list;

    public RecentQuizAdapter(Activity context, int layoutID, ArrayList<RecentQuizzes> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.recent_quiz_recycler_view_item,parent,false);
        return new RecentQuizAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.quizName.setText(list.get(position).getQuizName());
        holder.quizDescription.setText(list.get(position).getQuizDescription());
        holder.start.setOnClickListener(v -> {
            Intent intent = new Intent(context, StartQuizActivity.class);
            intent.putExtra("quizName",list.get(position).getQuizName());
            intent.putExtra("quizDescription",list.get(position).getQuizDescription());
            context.startActivity(intent);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView quizName,quizDescription;
        public Button start;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quizName = itemView.findViewById(R.id.quiz_name);
            quizDescription = itemView.findViewById(R.id.quiz_description);
            start = itemView.findViewById(R.id.btn_start);
        }
    }
}
