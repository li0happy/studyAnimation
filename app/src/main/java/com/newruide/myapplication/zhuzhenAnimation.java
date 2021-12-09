package com.newruide.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class zhuzhenAnimation extends AppCompatActivity {

    /**
     * 逐帧动画既可以通过XML实现，也可以通过JAVA代码实现
     * 在Android中，逐帧动画需要得到AnimationDrawable类的支持，将动画设置为view的背景，r然后调用start（）方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuzhen_animation);


    }
}