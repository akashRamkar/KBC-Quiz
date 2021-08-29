package com.example.demoquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter <myAdapter.holder>{
    String data [];

    public myAdapter(String[] data) {
       this. data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.singlelayout,parent,false);
        return new holder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
      holder.tex.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class holder extends RecyclerView.ViewHolder{
     ImageView img;
     TextView tex;
        public holder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id .imd1);
            tex=(TextView) itemView.findViewById(R.id.tex1);

        }
    }
}
