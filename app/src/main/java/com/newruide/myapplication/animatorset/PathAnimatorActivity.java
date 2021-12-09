package com.newruide.myapplication.animatorset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityPathAnimatorBinding;

public class PathAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPathAnimatorBinding binding = null;
    boolean mIsPop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_path_animator);
        binding.btn.setOnClickListener(this::onClick);
    }


    private void openAnimation(){
        doAnimateOpen(binding.btn1,0,5,300);
        doAnimateOpen(binding.btn2,1,5,300);
        doAnimateOpen(binding.btn3,2,5,300);
        doAnimateOpen(binding.btn4,3,5,300);
        doAnimateOpen(binding.btn5,4,5,300);
    }

    //该函数将每个按钮从初始位置移动到对应的结束位置
    private void doAnimateOpen(View view,int index,int total,int radius){
        if(view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index/((total - 1)*2) ;
        int translationX = -(int)(radius *Math.sin(degree));
        int translationY = -(int)(radius *Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1f)
        );
        set.setDuration(500).start();
    }
    private void closeAnimation(){
        doAnimateClose(binding.btn1,0,5,300);
        doAnimateClose(binding.btn2,1,5,300);
        doAnimateClose(binding.btn3,2,5,300);
        doAnimateClose(binding.btn4,3,5,300);
        doAnimateClose(binding.btn5,4,5,300);
    }

    private void doAnimateClose(View view,int index,int total,int radius){
        double degree = Math.PI *index /((total -1)*2);
        int translateX = (int)(radius * Math.sin(degree));
        int translateY = (int)(radius * Math.cos(degree));
        AnimatorSet set =new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",translateX,0),
                ObjectAnimator.ofFloat(view,"translationY",translateY,0),
                ObjectAnimator.ofFloat(view,"scaleX",1f,0.1f),
                ObjectAnimator.ofFloat(view,"scaleY",1f,0.1f),
                ObjectAnimator.ofFloat(view,"alpha",1f,0f)
        );
        set.setDuration(1500).start();
    }

    @Override
    public void onClick(View v) {
        if(!mIsPop){
            //弹出
            mIsPop =true;
            openAnimation();
        }else{
            //收回
            mIsPop =false;
            closeAnimation();
        }
    }
}