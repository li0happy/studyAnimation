package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

public class TelescopeView extends View {
    private Paint mPaint;
    private Bitmap bitmap;
    private Bitmap bitmapBg;

    public TelescopeView(Context context) {
        super(context);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.th);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int mDx;
    private int mDy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                mDx = (int) event.getX();
                mDy = (int) event.getY();
            }
            break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmapBg == null){
            //创建空白的位图
            bitmapBg = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvasBg = new Canvas(bitmapBg);
            //绘制背景图
            canvasBg.drawBitmap(bitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
        }
        if(mDx != -1 && mDy != -1){
            mPaint.setShader(new BitmapShader(bitmapBg, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx,mDy,150,mPaint);
        }
    }
}
