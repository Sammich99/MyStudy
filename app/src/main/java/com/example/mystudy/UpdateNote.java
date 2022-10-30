package com.example.mystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNote extends AppCompatActivity {

    EditText updateNoteTitle, updateNoteSubTitle, updateNoteText;
    Button updatedone;

    String title, subtitle, typenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_note);

        updateNoteTitle =  findViewById(R.id.updateNoteTitle);
        updateNoteSubTitle = findViewById(R.id.updateNoteSubTitle);
        updateNoteText =  findViewById(R.id.updateNoteText);

        updatedone = findViewById(R.id.updatedone);
        updatedone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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