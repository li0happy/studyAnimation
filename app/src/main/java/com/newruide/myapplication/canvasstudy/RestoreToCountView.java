package com.newruide.myapplication.canvasstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class RestoreToCountView extends View {
    private Paint paint;
    public RestoreToCountView(Context context) {
        super(context);
    }

    public RestoreToCountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Log.d("TAG",canvas.getSaveCount()+"");
        canvas.saveLayer(0,0,getWidth(),getHeight(),paint);
        Log.d("TAG",canvas.getSaveCount()+"");
    }

    public RestoreToCountView(Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RestoreToCountView(Context context, @Nullable  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
