package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

public class BitmapShaderView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
    /**
     * Shader是着色器，用于给空白图形上色。
     * Shader是一个基类，只有两个函数用于设置坐标变换矩阵
     * 其中一个派生类就是BitmapShader
     * public BitmapShader(Bitmap bitmap,TileMode tileX,TileMode tileY)
     * bitmap用来指定图案，TileMode用于指定当X轴Y轴超出单张图片大小时所使用的重复策略
     * TileMode的取值有三种：
     * TileMode.CLAMP:用边缘色彩来填充多余空间
     * TileMode.REPEAT:重复原图像来填充多余空间
     * TileMode.MIRROR:重复使用镜像模式的图像来填充多余空间
     */
}
