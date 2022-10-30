package com.example.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateDocuments extends AppCompatActivity {
    private EditText inputNoteTitle, inputNoteSubTitle,inputNoteText;
    Button savenote;

    NoteDatabase noteDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_documents);

        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = findViewById(R.id.inputNoteSubTitle);
        inputNoteText = findViewById(R.id.inputNoteText);
        savenote = findViewById(R.id.savenote);

        noteDB = new NoteDatabase(this);

        savenote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateDocuments.this, CourseActivity.class));

                String notetileTxt = inputNoteTitle.getText().toString();
                String notesubtitleTxt = inputNoteSubTitle.getText().toString();
                String notetypeTxt = inputNoteText.getText().toString();

                Boolean checkaddNote = noteDB.addNote(notetileTxt, notesubtitleTxt, notetypeTxt);
                if(checkaddNote == true) {
                    Toast.makeText(CreateDocuments.this, "Course Created", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CreateDocuments.this, "You Haven't Created Any Course", Toast.LENGTH_SHORT).show();
                }

            }
         });
      }
    }