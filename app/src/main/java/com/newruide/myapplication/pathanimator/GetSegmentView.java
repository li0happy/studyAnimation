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


public class GetSegmentView extends View {
    public GetSegmentView(Context context) {
        super(context);
    }

    Paint mPaint = null;
    Path mDestPath = null;
    Path mCirclePath = null;
    PathMeasure mPathMeasure = null;
    Float mCurAnimValue = 0f;
    public GetSegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        mDestPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100,100,50,Path.Direction.CW);
        mPathMeasure = new PathMeasure(mCirclePath,true);
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    public GetSegmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GetSegmentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        float stop = mPathMeasure.getLength() *mCurAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5))* mPathMeasure.getLength()));
        mDestPath.reset();
        mPathMeasure.getSegment(start,stop,mDestPath,true);
        canvas.drawPath(mDestPath,mPaint);
    }
}
