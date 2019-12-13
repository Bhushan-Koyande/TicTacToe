package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    private TextView head;
    private int splashTimeOut=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        head=findViewById(R.id.string1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashTimeOut);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        head.startAnimation(animation);
    }
}
