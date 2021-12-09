package com.newruide.myapplication.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

/**
 * SurfaceView双缓冲技术
 * Surface类是使用一种双缓冲的技术来渲染程序UI的
 * 一个被称为前端缓冲区，另一个被称为后端缓冲区
 * 前端缓冲区对应当前屏幕正在显示的内容，而后端缓冲区是接下来要渲染的图形缓冲区。
 * 通过lockCanvas()获得的是缓冲区是后端缓冲区，当绘图完成以后，
 * 调用unlockCanvasAndPost(canvas)将后端缓冲区与前端缓冲区进行交换，后端缓冲区变成变成前端缓冲区，将内容显示再屏幕之上。
 * 而原来的前端缓冲区则变成后端缓冲区。
 *
 *
 * Surface中缓冲画布的数量是根据需求分配的，如果用户获取画布的频率较慢，那么将会分配两块缓冲画布，否则将分配3得倍数块缓冲画布。
 */
public class DoubleBufferingView extends SurfaceView {

    public DoubleBufferingView(Context context) {
        super(context);
    }

    public DoubleBufferingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleBufferingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DoubleBufferingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private Paint mPaint;

    private  void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawText();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }
    private void drawText(){
        for(int i = 0;i<10;i++){
            Canvas canvas = getHolder().lockCanvas();
            if(canvas != null){
                canvas.drawText(i+"",i*30,50,mPaint);
            }
            getHolder().unlockCanvasAndPost(canvas);
        }

    }
}
