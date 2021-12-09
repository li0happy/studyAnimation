package com.newruide.myapplication.animatorimprove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.newruide.myapplication.R;

public class PropertyValuesHolderActivity extends AppCompatActivity {
    /**
     *PropertyValuesHolder类的含义，保存了动画过程中所需要操作的属性和对应的值。
     * 比如ofFloat()函数的内部实现就是将传入的参数封装成PropertyValuesHolder实例来保存动画状态的。
     * 在封装成为PropertyValuesHolder实例以后，后期的各种操作也是以PropertyValuesHolder为主的。
     * 因此，ObjectAnimator通过暴露PropertyValuesHolder得方法，让开发者可以通过PropertyValueHolder来构造动画。
     *
     *
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_values_holder);
    }
}