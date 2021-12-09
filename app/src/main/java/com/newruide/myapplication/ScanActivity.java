package com.newruide.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan2);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.scan_animation);
        animation1.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.scan_animation);
        animation2.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.scan_animation);
        animation3.setInterpolator(new AccelerateDecelerateInterpolator());
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.scan_animation);
        animation4.setInterpolator(new AccelerateDecelerateInterpolator());
        ImageView cirecle1 = findViewById(R.id.circle1);
        ImageView cirecle2 = findViewById(R.id.circle2);
        ImageView cirecle4 = findViewById(R.id.circle4);

        cirecle4.setOnClickListener(v ->{
            cirecle1.startAnimation(animation1);
            animation2.setStartOffset(800);
            cirecle2.startAnimation(animation2);
            animation3.setStartOffset(1400);
            animation4.setStartOffset(2000);
            cirecle4.startAnimation(animation4);
        });
    }
}