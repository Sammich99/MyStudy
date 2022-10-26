package com.example.mystudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateDocuments extends AppCompatActivity {
    private EditText inputNoteTitle, inputNoteSubTitle,inputNoteText;
    Button savenote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_documents);

        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = findViewById(R.id.inputNoteSubTitle);
        inputNoteText = findViewById(R.id.inputNoteText);
        savenote = findViewById(R.id.savenote);

        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteDatabase noteDB = new NoteDatabase(CreateDocuments.this);
                noteDB.addNote(inputNoteTitle.getText().toString().trim(),
                        inputNoteSubTitle.getText().toString().trim(),
                        inputNoteText.getText().toString().trim());
            }
         });
      }
    }