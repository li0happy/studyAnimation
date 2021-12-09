package com.newruide.myapplication.objectanimator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.newruide.myapplication.R;
import com.newruide.myapplication.databinding.ActivityObjectAnimatorBinding;

public class ObjectAnimatorActivity extends AppCompatActivity {

    /**
     * ValueAnimator只能对动画的数值进行计算，需要对哪个控件进行动画操作，就需要对ValueAnimator进行监听
     *ObjectAnimator动画的出现，就将动画与控件直接关联，避免了对动画的监听
     */
    ActivityObjectAnimatorBinding binding = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_object_animator);
        getAnimator();
    }

    private void getAnimator(){
        //一个参数指定操作的控件；第二个参数指的是操作的是控件的哪个属性；第三个参数是可变参数，是指这个属性如何变化
        //在View中已经实现了alpha、rotation、translate、scale的相关set函数
        //rotation表示围绕Z轴旋转；rotationX表示围绕X轴旋转；rotationY表示围绕Y轴旋转
        //translateX表示在X轴上的平移距离，以当前控件为原点，向右为正方向
        //translateY表示在Y轴上的平移距离，以当前控件为原点，向下为正方向
        //scaleX:在X轴上进行缩放倍数
        //scaleY:在Y轴上进行缩放倍数
        //注意：当且仅当动画只有一个过渡值，系统才会调用对应属性的get函数来得到动画的初始值；
        //当不存在get函数时，会取动画参数类型的默认值作为初始值；如果无法取得动画参数类型的默认值时，则会直接崩溃

        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.tv,"rotation",0,180);
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}