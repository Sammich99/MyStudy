package com.example.mystudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {
    private Context context;
    private ArrayList coursetitle_id, coursecode_id;
    private OnClickListener onClickListener;

    public CoursesAdapter(Context context, ArrayList coursetitle_id, ArrayList coursecode_id, OnClickListener onClickListener) {
        this.context = context;
        this.coursetitle_id = coursetitle_id;
        this.coursecode_id = coursecode_id;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.course_entry_container,parent,false);
        return new MyViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.coursetitle_id.setText(String.valueOf(coursetitle_id.get(position)));
        holder.coursecode_id.setText(String.valueOf(coursecode_id.get(position)));

    }

    @Override
    public int getItemCount()
    {
        return coursetitle_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView coursetitle_id, coursecode_id;
        OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            coursetitle_id =  itemView.findViewById(R.id.textcoursetitle);
            coursecode_id =  itemView.findViewById(R.id.textcoursecode);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            onClickListener.onClick(getAdapterPosition());
        }
    }
    public interface OnClickListener
    {
        void onClick(int position);
    }
}


