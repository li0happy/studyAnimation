package com.newruide.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.newruide.myapplication.databinding.ActivityShuXingAnimatorBinding;

public class ShuXingAnimator extends AppCompatActivity {

    /**
     * 属性动画包括ValueAnimator、ObjectAnimator
     * 视图动画是作用于控件的
     * 属性动画是作用于控件属性的，所以它能单独改变控件属性的值
     * 1、ValueAnimator
     * 这个动画是针对值的，ValueAnimator不会针对控件执行任何操作，可以给它设定从哪些值运动到哪些值，通过监听这些值的渐变过程来操作控件
     * ValueAnimator中共有两个监听器，animator.addUpdateListener,监听值的实时变化
     * AnimatorListener中，只要监听Animator的四个状态：start、end、cancel、repeat。
     * 移除监听器：removeListener()、removeAllListener()
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShuXingAnimatorBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_shu_xing_animator);
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        ValueAnimator animator1 = new ValueAnimator();
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                动画开始时，调用此函数通知动画使用者
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                动画结束时，调用此函数通知动画使用者
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                 动画取消时，会调用此函数通知动画使用者
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
//                动画重复时，会调用此函数通知动画使用者
            }
        });
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.end();
            }
        });
        animator.addUpdateListener(animation -> {
            int curValue = (int) animator.getAnimatedValue();
            binding.textView.layout(binding.textView.getLeft(),curValue,binding.textView.getRight(),curValue+binding.textView.getHeight());
        });

    }
}