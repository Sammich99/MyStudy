package com.example.mystudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    ImageView newnote;

    RecyclerView noterecyclerView;

    NoteDatabase noteDB;
    ArrayList<String> note_title, note_subtitle;
    NoteAdapter noteAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        noterecyclerView = findViewById(R.id.noteRecyclerView);
        newnote = (ImageView) findViewById(R.id.createNote);



        newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(CourseActivity.this, CreateDocuments.class);
                startActivity(intent);
            }
        });

        noteDB =  new NoteDatabase(CourseActivity.this);
        note_title = new ArrayList<>();
        note_subtitle = new ArrayList<>();


        storeDataInArrays();

        noteAdapter = new NoteAdapter(CourseActivity.this, note_title, note_subtitle);
        noterecyclerView.setAdapter(noteAdapter);
        noterecyclerView.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = noteDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                note_title.add(cursor.getString(0));
                note_subtitle.add(cursor.getString(1));

            }
        }
    }




}
