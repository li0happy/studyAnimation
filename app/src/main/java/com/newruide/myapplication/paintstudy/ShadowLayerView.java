package com.newruide.myapplication.paintstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

/**
 * 用于添加阴影效果的函数setShadowLayer()
 * 该函数只有文字绘制阴影支持硬件加速，其他的都不支持硬件加速
 * clearShadowLayer()函数用于清除阴影
 */
public class ShadowLayerView extends View {
    private Paint mPaint=new Paint();
    private Bitmap mBitmap;
    public ShadowLayerView(Context context) {
        super(context);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(25);
        mPaint.setShadowLayer(1,10,10,Color.GRAY);//设置阴影
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shadow);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawText("肉肉酱",100,100,mPaint);
        canvas.drawCircle(300,100,50,mPaint);
        //显示的效果可见，setShadowLayer()函数对图片不起作用
        canvas.drawBitmap(mBitmap,null,new Rect(500,50,500+mBitmap.getWidth(),50+mBitmap.getHeight()),mPaint);

    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
