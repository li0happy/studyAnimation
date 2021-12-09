package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 实现手势轨迹很简单，只需要在自定义控件中拦截OnTouchEvent,然后根据手指的移动轨迹来就绘制Path
 */
public class NormalGestureTrackView extends View {
    private Path mPath = new Path();
    private Paint mPaint;
    public NormalGestureTrackView(Context context) {
        super(context);
    }

    public NormalGestureTrackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    private float mPreX,mPreY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                //返回true表示当前控件已经消费了这个动作
                // 之后的一些动作会传递进入这个控件
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                float endX = (mPreX +event.getX())/2;
                float endY = (mPreY +event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                //这里重绘控件使用的是postInvalidate函数，invalidate函数也可以用于重绘，但是它必须执行在主线程中
                //而postInvalidate函数则不需要，任意线程中都可以使用
                postInvalidate();
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            }
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath,mPaint);
    }

    public NormalGestureTrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NormalGestureTrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
