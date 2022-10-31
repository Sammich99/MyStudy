package com.example.mystudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity implements ExternalResourcesAdapter.OnClickListener{
    ImageView newnote;
    ImageButton imageView;

    RecyclerView recyclerView;

    ExternalResourceDatabase noteDB;
    ArrayList<String> inputNoteTitle, inputNoteSubTitle, inputNoteText;
    ExternalResourcesAdapter externalResourcesAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        recyclerView = findViewById(R.id.noteRecyclerView);
        newnote = (ImageView) findViewById(R.id.createNote);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, CourseList.class);
                startActivity(intent);
            }
        });



        newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(CourseActivity.this, CreateExternalResources.class);
                startActivity(intent);
            }
        });

        noteDB =  new ExternalResourceDatabase(this);
        inputNoteTitle = new ArrayList<>();
        inputNoteSubTitle = new ArrayList<>();
        inputNoteText = new ArrayList<>();


        storeDataInArrays();

        externalResourcesAdapter = new ExternalResourcesAdapter(this, inputNoteTitle, inputNoteSubTitle, inputNoteText, this);
        recyclerView.setAdapter(externalResourcesAdapter);
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
        Intent intent = new Intent(this, UpdateExternalResources.class);
        startActivity(intent);
    }


}
