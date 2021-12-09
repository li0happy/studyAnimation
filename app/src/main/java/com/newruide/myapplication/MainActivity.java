package com.newruide.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.newruide.myapplication.databinding.ActivityMainBinding;

/**
 * 动画分为：视图动画、属性动画
 * （1）视图动画：对某个控件实现动画操作，包含补间动画和逐帧动画
 * （2）属性动画：对属性进行动画设置，然后观察属性，操作控件完成相应的动画操作，包含ValueAnimator、ObjectAnimator
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //采用XML的方法生成
                Animation animator = AnimationUtils.loadAnimation(MainActivity.this,R.anim.viewanimation); //首先加载动画
                binding.image.startAnimation(animator);
            }
        });
        binding.btn.setOnClickListener(v -> {
            //采用代码的方法生成
            //1、ScaleAnimation类对应scale标签
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.4f, 2.0f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
            scaleAnimation.setDuration(10000);
            //2、AlphaAnimation类对应alpha标签
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
            alphaAnimation.setFillAfter(true);
            //3、RotateAnimayion类对应rotate标签
            RotateAnimation rotateAnimation =new RotateAnimation(-3000, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f );
            rotateAnimation.setDuration(3000);
            //4、TranslateAnimation类对应translate标签
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 100, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 200);
            translateAnimation.setDuration(4000);
            //AnimationSet类对应set标签
            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(rotateAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.setInterpolator(new AccelerateDecelerateInterpolator());//设置插值器
            binding.image.startAnimation(animationSet);
        });
    }
}