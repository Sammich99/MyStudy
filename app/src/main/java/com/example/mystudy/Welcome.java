package com.example.mystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {


    Animation topanim, bottomanim;
    ImageView logoimage2;
    TextView logoslogan2, logoslogan3;
    Button SignUpButton2, LoginButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.second_splashscreen);

        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim =  AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logoimage2 = findViewById(R.id.logoimage2);
        logoslogan2 = findViewById(R.id.logoslogan2);
        logoslogan3 = findViewById(R.id.logoslogan3);
        SignUpButton2 = findViewById(R.id.SignUpButton2);
        LoginButton2 = findViewById(R.id.LoginButton2);


        logoimage2.setAnimation(topanim);
        logoslogan2.setAnimation(bottomanim);
        logoslogan3.setAnimation(bottomanim);
        SignUpButton2.setAnimation(bottomanim);
        LoginButton2.setAnimation(bottomanim);


        SignUpButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        LoginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
            }
        });


    }
}