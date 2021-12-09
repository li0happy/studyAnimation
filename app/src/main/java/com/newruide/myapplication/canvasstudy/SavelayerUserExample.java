package com.newruide.myapplication.canvasstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

public class SavelayerUserExample extends View {
    private Paint paint;
    private Bitmap bitmap;

    public SavelayerUserExample(Context context) {
        super(context);
    }

    public SavelayerUserExample(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.th,options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
        //新建一块画布，后续所有的操作都是在这块画布上进行的
        int layerId = canvas.saveLayer(new RectF(0,0,getWidth(),getHeight()),paint,Canvas.ALL_SAVE_FLAG);
        canvas.skew(1.732f,0);
        canvas.drawRect(0,0,150,160,paint);
        canvas.restoreToCount(layerId);
    }

    public SavelayerUserExample(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SavelayerUserExample(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
