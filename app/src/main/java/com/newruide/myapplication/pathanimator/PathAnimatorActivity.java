package com.newruide.myapplication.pathanimator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.newruide.myapplication.R;

public class PathAnimatorActivity extends AppCompatActivity {

    /**
     *利用PathMeasure实现路径动画
     * PathMeasure类似一个计算器，可以计算出指定路劲的一些信息，比如路径总长，指定长度对应的坐标点等。
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_animator2);

    }
}