package com.newruide.myapplication;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class LoadImageView extends ImageView {
    private int mTop;
    private int mCurrentPictureIndex = 0;
    private int mCount = 3;
    public LoadImageView(Context context) {
        super(context);
        init();
    }

    public LoadImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LoadImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }
    private void init(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100,0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (int)animation.getAnimatedValue();
                setTop(mTop-dx);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                设置起始图片
//                setImageDrawable(getResources().getDrawable());
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
//                设置重复时候的图片
                mCurrentPictureIndex++;
                switch (mCurrentPictureIndex%mCount){
                    case 0:
//                        setImageDrawable(getResources().getDrawable());
                        break;
                    case 1:
//                        setImageDrawable(getResources().getDrawable());
                        break;
                    case 2:
//                        setImageDrawable(getResources().getDrawable());
                        break;
                }
            }
        });
        valueAnimator.start();
    }
}
