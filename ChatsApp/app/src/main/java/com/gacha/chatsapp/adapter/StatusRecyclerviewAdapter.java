package com.gacha.chatsapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gacha.chatsapp.R;

import java.util.ArrayList;

public class StatusRecyclerviewAdapter extends RecyclerView.Adapter<StatusRecyclerviewAdapter.MyViewHolder> {
    private ArrayList<String> list;
    private int layoutId;
    private Activity context;

    public StatusRecyclerviewAdapter(Activity context,int layoutId,ArrayList<String> list){
        this.list = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.status,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusRecyclerviewAdapter.MyViewHolder holder, int position) {
        String a = list.get(position);
        holder.txt.setText(a);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textView);
        }
    }
}
