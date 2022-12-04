package com.example.mystudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation topanim, bottomanim;
    ImageView logoimage;
    TextView logoslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim =  AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logoimage = findViewById(R.id.logoimage);
        logoslogan = findViewById(R.id.logoslogan);

        logoimage.setAnimation(topanim);
        logoslogan.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, Welcome.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}