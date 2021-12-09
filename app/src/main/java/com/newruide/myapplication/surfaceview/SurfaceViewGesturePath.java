package com.newruide.myapplication.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewGesturePath  extends SurfaceView {

    public SurfaceViewGesturePath(Context context) {
        super(context);
        init();
    }

    public SurfaceViewGesturePath(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewGesturePath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SurfaceViewGesturePath(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private Paint mPaint;
    private Path mPath;
    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0x00ff00);
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                mPath.moveTo(x,y);
                return true;
            }
            case MotionEvent.ACTION_MOVE:{
                mPath.lineTo(x,y);
            }
            drawCanvas1();
        }
        return super.onTouchEvent(event);
    }
    //绘制
    //onTouchEvent是在主线程中执行的，drawCanvas虽然是绘制的缓冲画布，但是也是在主线程中执行的。
    //因此需要将绘制缓冲画布写到子线程中
    private void drawCanvas(){
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        canvas.drawPath(mPath,mPaint);
        holder.unlockCanvasAndPost(canvas);
    }
    private void drawCanvas1(){
        new Thread(() -> {
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();
            canvas.drawPath(mPath,mPaint);
            holder.unlockCanvasAndPost(canvas);
        }).start();
    }
}
