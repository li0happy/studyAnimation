package com.newruide.myapplication.viewgroupanimator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityViewGroupAnimatorBinding;

public class ViewGroupAnimatorActivity extends AppCompatActivity {

    /**
     * 向ViewGroup内的组件添加动画
     * LayoutTransition类可以实现在ViewGroup中动态添加或者删除其中
     * 的控件时指定动画
     *虽然在ViewGroup得XML中添加一行代码 android:animateLayoutChanges="true"
     * 即可实现内部控件在添加、删除时都带有动画效果，但是只能使用默认动画效果
     * 无法自定义动画
     */
    ActivityViewGroupAnimatorBinding binding = null;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_group_animator);
        //指定动画
        LayoutTransition transition = new LayoutTransition();
        ObjectAnimator animatorIn = ObjectAnimator.ofFloat(null,"rotationY",0f,360f,0f);
        transition.setAnimator(LayoutTransition.APPEARING,animatorIn);
        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(null,"rotation",0f,90f,0f);
        transition.setAnimator(LayoutTransition.DISAPPEARING,animatorOut);
        binding.linearContainer.setLayoutTransition(transition);
        binding.btnAdd.setOnClickListener(v -> addButtonView());
        binding.btnRemove.setOnClickListener(v -> removeButtonView());
    }

    //添加控件
    private void addButtonView(){
        i++;
        Button button = new Button(this);
        //设置按钮的宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setText("test");
        binding.linearContainer.addView(button,0);
    }
    //删除控件
    private void removeButtonView(){
        if(i>0){
            binding.linearContainer.removeViewAt(0);
            i--;
        }
    }
}