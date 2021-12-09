package com.newruide.myapplication;

import android.animation.TimeInterpolator;

//插值器用来控制动画的区间如何被计算出来
//动画区间的值 = 区间起始值+（区间结束值 - 区间起始值）*显示进度
//可以通过指定getInterpolation的返回值来指定当前显示的进度值
//自定义插值器，只需要实现TimeInterpolator接口
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1-input;
    }
}
