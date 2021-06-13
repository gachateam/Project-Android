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
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.quizapp.Model.Ques;
import com.gacha.quizapp.Model.Unit;
import com.gacha.quizapp.Model.UserHistory;
import com.gacha.quizapp.R;
import com.gacha.quizapp.StartQuizActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RecentQuizAdapter extends RecyclerView.Adapter<RecentQuizAdapter.MyViewHolder> {
    private Activity context;
    private int layoutID;
    private ArrayList<Unit> list;
    private ArrayList<Ques> listQues;
    private UserHistory userHistory;

    public void setList(ArrayList<Unit> list) {
        for (Unit unit : list) {
            Log.d(TAG, "setList: "+unit);
        }
        this.list = list;
    }

    private static final String TAG = RecentQuizAdapter.class.getSimpleName();

    public RecentQuizAdapter(Activity context, int layoutID, ArrayList<Unit> list, ArrayList<Ques> listQues) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
        this.listQues = listQues;
        userHistory = new UserHistory();
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
        userHistory.getHistoryPoint(list.get(position).getId(),holder.userHistory);
        holder.quizName.setText(list.get(position).getName());
        holder.quizDescription.setText(list.get(position).getDescription());
        holder.start.setOnClickListener(v -> {
            Intent intent = new Intent(context, StartQuizActivity.class);
            intent.putExtra("name",list.get(position).getName());
            intent.putExtra("description",list.get(position).getDescription());
            intent.putExtra("ques",list.get(position).getQuesList(listQues));
            intent.putExtra("unitId",list.get(position).getId());
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
        public TextView quizName,quizDescription,userHistory;
        public Button start;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            quizName = itemView.findViewById(R.id.quiz_name);
            quizDescription = itemView.findViewById(R.id.quiz_description);
            userHistory = itemView.findViewById(R.id.userHistory);
            start = itemView.findViewById(R.id.btn_start);
        }
    }
}
