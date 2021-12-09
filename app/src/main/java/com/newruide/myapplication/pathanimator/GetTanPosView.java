package com.newruide.myapplication.pathanimator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.newruide.myapplication.R;

/**
 * getMatrix()函数用于得到路径上某一长度的位置
 * 以及该位置的正切值的矩阵
 */


public class GetTanPosView extends View {
    private Path mCirclePath,mDestPath; //圆圈路径、绘制路径
    private Paint mPaint;
    private PathMeasure mPathMeasure;//路径计算器
    private float mCurrentAnimValue; //当前动画进度
    private Bitmap mArrawBitmap;
    private float[] pos = new float[2];//存储箭头图标的坐标
    private float[] tan = new float[2];//存储箭头的正切值
    public GetTanPosView(Context context) {
        super(context);
    }

    public GetTanPosView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔，路径，动画
        setLayerType(LAYER_TYPE_HARDWARE,null);//基于软件的绘制模式
        mArrawBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.right_arraw);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mCirclePath = new Path();
        mDestPath = new Path();
        mCirclePath.addCircle(100,100,50, Path.Direction.CW);//顺时针
        mPathMeasure = new PathMeasure(mCirclePath,true);
        //初始化动画
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentAnimValue = (float)animation.getAnimatedValue();
                invalidate();//启用重绘制
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        //计算曲线的起始位置与结束位置
        float stop = mPathMeasure.getLength() * mCurrentAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mCurrentAnimValue - 0.5))* mPathMeasure.getLength()));
        mDestPath.reset();
        mPathMeasure.getSegment(start,stop,mDestPath,true);
        //绘制
        canvas.drawPath(mDestPath,mPaint);
        //旋转箭头图片并绘制
        mPathMeasure.getPosTan(stop,pos,tan);
        float degrees = (float)(Math.atan2(tan[1],tan[0])*180.0 /Math.PI);
        Matrix matrix = new Matrix();
        //图片以图片中心为锚点旋转
        matrix.postRotate(degrees,mArrawBitmap.getWidth()/2,mArrawBitmap.getHeight()/2);
        matrix.postTranslate(pos[0] - mArrawBitmap.getWidth()/2,pos[1] - mArrawBitmap.getHeight()/2);
        canvas.drawBitmap(mArrawBitmap,matrix,mPaint);
    }

    public GetTanPosView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GetTanPosView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
