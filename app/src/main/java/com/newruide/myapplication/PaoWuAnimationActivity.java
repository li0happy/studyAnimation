package com.newruide.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.newruide.myapplication.databinding.ActivityPaoWuAnimationBinding;

public class PaoWuAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPaoWuAnimationBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_pao_wu_animation);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始动画
                ValueAnimator animator = ValueAnimator.ofObject(new MyPaoWuEvaluaror(),new Point(0,0),new Point(400,300));
                animator.setInterpolator(new LinearInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    Point currentPoint = null;
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //根据属性动画计算的值，来定位小球的位置
                        currentPoint = (Point)animation.getAnimatedValue();
                        binding.imageView.layout(currentPoint.x,currentPoint.y,
                                (currentPoint.x + binding.imageView.getWidth()),
                                (currentPoint.y + binding.imageView.getHeight()));
                    }
                });
                animator.setDuration(2000);
                animator.start();
            }
        });
/*        Path path=new Path();
        path.moveTo(0,0);
        path.quadTo(10,10,30,60);
        Bitmap bitmap = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        Paint p =new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(10.0f);
        canvas.drawPath(path,p);
        Bitmap b=bitmap;*/
     }
     class MyPaoWuEvaluaror implements TypeEvaluator<Point>{
         @Override
         public Point evaluate(float fraction, Point startValue, Point endValue) {
             Point point = new Point();
             point.x = startValue.x +(int)((endValue.x - startValue.x) * fraction);
             if(fraction < 0.5){
                 point.y = startValue.y +(int)(fraction *2 * (endValue.y - startValue.y));
             }else{
                 point.y = endValue.y;
             }
             return point;
         }
     }
}