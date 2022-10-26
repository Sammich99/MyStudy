package com.example.mystudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_title, note_subtitle;

    NoteAdapter(Context context,
                ArrayList note_title,
                ArrayList note_subtitle) {
        this.context = context;
        this.note_title = note_title;
        this.note_subtitle = note_subtitle;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notecontainer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.note_title_txt.setText(String.valueOf(note_title.get(position)));
        holder.note_subtitle_txt.setText(String.valueOf(note_subtitle.get(position)));

    }

    @Override
    public int getItemCount() {
        return note_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_title_txt, note_subtitle_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title_txt = itemView.findViewById(R.id.note_title_txt);
            note_subtitle_txt = itemView.findViewById(R.id.note_subtitle_txt);
        }
    }
}
