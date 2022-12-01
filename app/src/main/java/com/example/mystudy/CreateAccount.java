package com.example.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {

    Button SignUpButton;
    TextView LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        SignUpButton =  findViewById(R.id.SignUpButton);
        LoginButton = findViewById(R.id.LoginButton);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(CreateAccount.this, Login.class);
                startActivity(intent);
            }
        });


    }
}