package com.gacha.chatsapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gacha.chatsapp.R;
import com.gacha.chatsapp.adapter.StatusRecyclerviewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private StatusRecyclerviewAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlist);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.hide();
        ArrayList<String> a = new ArrayList<>();
        a.add("test");
        a.add("test1");
        a.add("test1");
        a.add("test13");
        a.add("test15");
        a.add("test12");
        RecyclerView recyclerView = findViewById(R.id.statusList);
        statusAdapter = new StatusRecyclerviewAdapter(this,R.id.status,a);
        GridLayoutManager layoutManager = new GridLayoutManager(this,11);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(statusAdapter);
    }
}