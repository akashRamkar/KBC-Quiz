package com.example.demoquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class HistoryActivity extends AppCompatActivity {
RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//      Intent intent=getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rec=(RecyclerView)findViewById(R.id.recview);
        rec.setLayoutManager(new LinearLayoutManager(this));
        String tempScoreString= Integer.toString(ScoreActivity.tempScore);
        String arr[]={tempScoreString};
        rec.setAdapter(new myAdapter(arr));
    }
}