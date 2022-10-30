package com.example.mystudy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private Context context;
    private ArrayList note_title, note_subtitle, note_typenote;



    NoteAdapter(Context context,
                ArrayList note_title,
                ArrayList note_subtitle,
                ArrayList note_typenote
    ) {
        this.context = context;
        this.note_title = note_title;
        this.note_subtitle = note_subtitle;
        this.note_typenote = note_typenote;




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notecontainer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.note_title.setText(String.valueOf(note_title.get(position)));
        holder.note_subtitle.setText(String.valueOf(note_subtitle.get(position)));
        holder.updateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNote.class);
                intent.putExtra("title", note_title.get(holder.getAdapterPosition()).toString());
                intent.putExtra("subtitle", note_subtitle.get(holder.getAdapterPosition()).toString());
                intent.putExtra("typenote", note_typenote.get(holder.getAdapterPosition()).toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return note_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note_title, note_subtitle;
        CardView updateLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title = itemView.findViewById(R.id.note_title_txt);
            note_subtitle = itemView.findViewById(R.id.note_subtitle_txt);
            updateLayout = itemView.findViewById(R.id.openNote);
        }
    }

}
