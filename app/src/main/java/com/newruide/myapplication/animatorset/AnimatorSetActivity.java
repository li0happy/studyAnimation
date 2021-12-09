package com.newruide.myapplication.animatorset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityAnimatorSetBinding;

public class AnimatorSetActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * ValueAnimator和ObjectAnimator只能单独实现一个动画
     * 如果想要使用一个组合动画，就需要用到AnimatorSet
     *AnimatorSet提供了两个函数，playSequentially()  playTogether()。
     * 前者表示所有动画依次播放，后者表示所有动画一起开始。
     *
     * 比如有三个动画，想要先播放C，然后同时播放A和B，利用playSequentially()函数和playTogether()方法是无法实现的
     * 此时需要AnimatorSet.Builder
     * 
     */
    ActivityAnimatorSetBinding binding =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_animator_set);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
    }

    //依次播放
    private void getSequentiallyAnimator(){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.img1,"translationX",0,100,-100,0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.img2,"scaleY",1,2,3,4,3,2,1,0.5f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(binding.tv,"rotation",0,180,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1,animator2,animator3);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
    //同时播放
    private void getTogeAnimator(){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.img1,"translationX",0,100,-100,0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.img2,"scaleY",1,2,3,4,3,2,1,0.5f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(binding.tv,"rotation",0,180,0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1,animator2,animator3);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:{
                getSequentiallyAnimator();
            }break;
            case R.id.btn2:{
                getTogeAnimator();
            }break;
        }
    }
}