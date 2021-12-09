package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

public class EraserView extends View {
    private Bitmap srcBitmap;
    private Paint paint;
    private Bitmap destBitmap;
    private Path mPath;

    public EraserView(Context context) {
        super(context);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(45);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.th, options);
        destBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mPath = new Path();
    }

    float preX;
    float preY;
    float endX;
    float endY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(event.getX(), event.getY());
                preX = event.getX();
                preY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE:
                endX = (preX + event.getX()) / 2;
                endY = (preY + event.getY()) / 2;
                mPath.quadTo(preX, preY, endX, endY);
                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId = canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        //先把手势轨迹画在目标图像上
        Canvas canvas1 = new Canvas(destBitmap);
        canvas1.drawPath(mPath,paint);
        //再把目标图像画到画布上
        canvas.drawBitmap(destBitmap,0,0,paint);
        //计算源图像区域
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(srcBitmap,0,0,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
