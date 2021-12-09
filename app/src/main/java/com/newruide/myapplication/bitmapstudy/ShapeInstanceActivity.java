package com.newruide.myapplication.bitmapstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityShapeInstanceBinding;

public class ShapeInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_instance);
        ActivityShapeInstanceBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_shape_instance);
        //当单击按钮的时候，给Shape标签添加圆角
        //shape 标签是用过控件background引入的。，对它进行更改需要强转成GradientdDrawable
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = (GradientDrawable) binding.btn.getBackground();
                drawable.setGradientRadius(20);
            }
        });
    }
}