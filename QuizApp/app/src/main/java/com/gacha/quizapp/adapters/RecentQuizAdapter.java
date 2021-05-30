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

import com.gacha.quizapp.Model.QuesMul;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartQuizActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class RecentQuizAdapter extends RecyclerView.Adapter<RecentQuizAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<Unit> list;
    private ArrayList<QuesMul> listQues;

    public RecentQuizAdapter(Activity context, int layoutID, ArrayList<Unit> list, ArrayList<QuesMul> listQues) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
        this.listQues = listQues;
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
        holder.quizName.setText(list.get(position).getName());
        holder.quizDescription.setText(list.get(position).getDescription());
        holder.start.setOnClickListener(v -> {
            Intent intent = new Intent(context, StartQuizActivity.class);
            intent.putExtra("name",list.get(position).getName());
            intent.putExtra("description",list.get(position).getDescription());
            intent.putExtra("ques",getQuesList(list.get(position).getQues()));
            context.startActivity(intent);
        });
    }

    private ArrayList<QuesMul> getQuesList(ArrayList<Integer> listQues){
        ArrayList<QuesMul> quesMul = new ArrayList<>();

        for (Integer quesId : listQues) {
            for (QuesMul quesMu : this.listQues) {
                if (quesMu.getQuesId() == quesId){
                    quesMul.add(quesMu);
                    break;
                }
            }
        }

        return quesMul;
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
