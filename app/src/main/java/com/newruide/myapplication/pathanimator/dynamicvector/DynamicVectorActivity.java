package com.newruide.myapplication.pathanimator.dynamicvector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.graphics.drawable.Animatable;
import android.os.Bundle;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityDynamicVectorBinding;

public class DynamicVectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDynamicVectorBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_dynamic_vector);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this,R.drawable.line_animated_vector);
        binding.img.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable)binding.img.getDrawable()).start();
    }
}