package com.newruide.myapplication.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.newruide.myapplication.R;

public class AnimationSurfaceView extends SurfaceView {
    public AnimationSurfaceView(Context context) {
        super(context);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimationSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                falg =true;
                startAnimation();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                falg = false;
            }
        });
    }
    private SurfaceHolder surfaceHolder;
    private boolean falg;
    private Bitmap bitmap;
    private float mSurfaceWidth,mSurfaceHeight;
    private int mBitPos;
    private Canvas canvas;
    private Thread thread;
    //背景移动状态
    private enum State{
        LEFT,RIGHT
    }
    //默认左
    private State state = State.LEFT;
    //背景画面移动步骤
    private final int BitmapStep = 1;
    //开始自动动画
    private void startAnimation(){
        mSurfaceWidth = getWidth();
        mSurfaceHeight = getHeight();
        //将图片放大到2分之三的宽
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.th);
        bitmap = Bitmap.createScaledBitmap(originalBitmap,(int)(mSurfaceWidth*3/2),(int)mSurfaceHeight,true);
        //开启线程开始绘图
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(falg){
                    canvas = surfaceHolder.lockCanvas();
                    //绘图
                    drawView();
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    private void drawView(){
        //绘制背景
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bitmap,mBitPos,0,null);
        switch (state){
            case LEFT:{
                mBitPos -= BitmapStep;
            }break;
            case RIGHT:{
                mBitPos += BitmapStep;
            }break;
        }
        if(mBitPos <= - mSurfaceWidth/2){
            state = State.RIGHT;
        }
        if(mBitPos >= 0){
            state = State.LEFT;
        }
    }
}
