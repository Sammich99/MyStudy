package com.example.mystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateExternalResources extends AppCompatActivity {

    EditText updateNoteTitle, updateNoteSubTitle, updateNoteText, inputNoteTitle, inputNoteSubTitle, inputNoteText;
    Button updatedone;
    ImageView imageView;

    String title, subtitle, typenote;
    ExternalResourceDatabase noteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_external_resources);

        updateNoteTitle =  findViewById(R.id.updateNoteTitle);
        updateNoteSubTitle = findViewById(R.id.updateNoteSubTitle);
        updateNoteText =  findViewById(R.id.updateNoteText);
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubTitle = findViewById(R.id.inputNoteSubTitle);
        inputNoteText = findViewById(R.id.inputNoteText);
        imageView = findViewById(R.id.imageView);
        noteDB = new ExternalResourceDatabase(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateExternalResources.this, CourseActivity.class));
            }
        });

        updatedone = findViewById(R.id.updatedone);
        updatedone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateExternalResources.this, CourseActivity.class));
                ExternalResourceDatabase noteDB = new ExternalResourceDatabase(UpdateExternalResources.this);

                title = updateNoteTitle.getText().toString().trim();
                subtitle = updateNoteSubTitle.getText().toString().trim();
                typenote = updateNoteText.getText().toString().trim();
                noteDB.updateNote(title, subtitle, typenote);




            }
        });

        getAndSetIntentData();

    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("title") && getIntent().hasExtra("subtitle") && getIntent().hasExtra("typenote")) {
            title = getIntent().getStringExtra("title");
            subtitle = getIntent().getStringExtra("subtitle");
            typenote = getIntent().getStringExtra("typenote");


            updateNoteTitle.setText(title);
            updateNoteSubTitle.setText(subtitle);
            updateNoteText.setText(typenote);

        } else
        {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}