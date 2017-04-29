package com.example.fiori.aboutme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

///**
// * Created by Fiori on 4/26/2017.
// */
//
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[][] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;

    public RecyclerViewAdapter(Context context1,String[][] SubjectValues1){
        SubjectValues = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView, textView2;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.subject_textview);
            textView2 = (TextView)v.findViewById(R.id.subject_textview2);

        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items,parent,false);
        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView.setText(SubjectValues[position][0]);
        holder.textView2.setText(SubjectValues[position][1]);
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}