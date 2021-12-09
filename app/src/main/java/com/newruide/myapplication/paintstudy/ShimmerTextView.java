package com.newruide.myapplication.paintstudy;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ShimmerTextView extends TextView {
    private  int mDx;
    private Paint mpaint;
    private LinearGradient mLinearGradient;
    public ShimmerTextView(Context context) {
        super(context);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mpaint = getPaint();
        int length = (int)mpaint.measureText(getText().toString());
        //根据文字长度创建动画
        createAnim(length);
        createLinearGradient(length);
    }
    private void createAnim(int length){
        ValueAnimator animator = ValueAnimator.ofInt(0,2*length);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(2000);
        animator.start();
    }
    private  void createLinearGradient(int length){
        mLinearGradient = new LinearGradient(-length,0,0,0,
                new int[]{getCurrentTextColor(),0xff00ff00,getCurrentTextColor()},
                new float[]{0,0.5f,1},
                Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(mDx,0);
        mLinearGradient.setLocalMatrix(matrix);
        mpaint.setShader(mLinearGradient);
        super.onDraw(canvas);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
