package com.example.mystudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity implements NoteAdapter.OnClickListener{
    ImageView newnote;

    RecyclerView recyclerView;

    NoteDatabase noteDB;
    ArrayList<String> inputNoteTitle, inputNoteSubTitle, inputNoteText;
    NoteAdapter noteAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        recyclerView = findViewById(R.id.noteRecyclerView);
        newnote = (ImageView) findViewById(R.id.createNote);



        newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(CourseActivity.this, CreateDocuments.class);
                startActivity(intent);
            }
        });

        noteDB =  new NoteDatabase(this);
        inputNoteTitle = new ArrayList<>();
        inputNoteSubTitle = new ArrayList<>();
        inputNoteText = new ArrayList<>();


        storeDataInArrays();

        noteAdapter = new NoteAdapter(this, inputNoteTitle, inputNoteSubTitle, inputNoteText, this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void storeDataInArrays() {
        Cursor cursor = noteDB.readAllData();
        if(cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                inputNoteTitle.add(cursor.getString(0));
                inputNoteSubTitle.add(cursor.getString(1));
                inputNoteText.add(cursor.getString(2));


            }
        }
    }

    @Override
    public void onClick(int position) {
        inputNoteTitle.get(position);
        inputNoteSubTitle.get(position);
        inputNoteText.get(position);
        Intent intent = new Intent(this, UpdateNote.class);
        startActivity(intent);
    }


}
