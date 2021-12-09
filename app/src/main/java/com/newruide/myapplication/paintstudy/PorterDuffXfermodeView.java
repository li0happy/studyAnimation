package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

/**
 * 混合模式，能够将两张图片无缝结合
 * PorterDuffXfermode构造函数只有一个参数，表示混合模式
 * 枚举值有18个，表示18种混合模式，每种模式都对应着一种算法
 * 常见的模式有：
 * Mode.ADD(饱和度相加)
 * Mode.LIGHTEN(变亮)
 * Mode.DARKEN(变暗)
 * Mode.MULTIPLY(正片叠底)
 * Mode.OVERLAY(叠加)
 * Mode.SCREEN(滤色)
 */
public class PorterDuffXfermodeView extends View {
    private int width = 200;
    private int height = 200;
    private Bitmap destBitmap;
    private Bitmap srcBitmap;
    private Paint mPaint;
    public PorterDuffXfermodeView(Context context) {
        super(context);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);//禁用硬件加速
        destBitmap = makeDest(width,height);
        srcBitmap = makeSrc(width,height);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId = canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(destBitmap,0,0,mPaint);
        //在Xfermode设置前画出的图像叫作目标图像，即给谁应用Xfermode
        //在Xfermode设置后的图像叫作源图像，即拿什么应用Xfermode
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap,width/2,height/2,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    private Bitmap makeDest(int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);//构建位图
        Canvas canvas = new Canvas(bitmap);//以当前位图作为绘图背景
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0,0,width,height),paint);
        return bitmap;
    }
    private Bitmap makeSrc(int width,int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(new RectF(0,0,width,height),paint);
        return bitmap;
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
