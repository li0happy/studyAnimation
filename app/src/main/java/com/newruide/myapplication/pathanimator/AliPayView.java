package com.newruide.myapplication.pathanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class AliPayView extends View {
    private Paint mPaint;
    private Path mCirclePath,mDestPath;
    private PathMeasure mPathMeasure;
    private float mCenterX = 100;
    private float mCenterY = 100;
    private float mRadius = 50;
    private float mCurrentValue;

    public AliPayView(Context context) {
        super(context);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        mCirclePath = new Path();
        mDestPath = new Path();

        mCirclePath.addCircle(mCenterX,mCenterY,mRadius, Path.Direction.CW);
        mCirclePath.moveTo(mCenterX- mRadius/2,mCenterY);
        mCirclePath.lineTo(mCenterX,mCenterY+mRadius/2);
        mCirclePath.lineTo(mCenterX+mRadius/2,mCenterY-mRadius/3);
        mPathMeasure = new PathMeasure(mCirclePath,false);

        ValueAnimator animator = ValueAnimator.ofInt(0,200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentValue = (int)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
        if(mCurrentValue < 100){
            float stop = (mCurrentValue/100) * mPathMeasure.getLength();
            mPathMeasure.getSegment(0,stop,mDestPath,true);
        }else if(mCurrentValue == 100){
            mPathMeasure.getSegment(0,mPathMeasure.getLength(),mDestPath,false);
            mPathMeasure.nextContour();
        }
        else{
            float stop = mPathMeasure.getLength() *((float)(mCurrentValue - 100)/100);
            mPathMeasure.getSegment(0,stop,mDestPath,true);
        }
        canvas.drawPath(mDestPath,mPaint);
    }
}
