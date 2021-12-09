package com.newruide.myapplication.bitmapstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowId;

import androidx.annotation.Nullable;

public class ShapeStudy {
    /**
     * ShapeDrawable是需要与Shape对象关联起来的
     * Shape是一个基类，其中的draw函数是一个虚函数，每个子类可以根据不同的需求来绘出不同的图形
     * Shape的派生类有：RectShape、ArcShape、OvalShape、 RoundRectShape、PathShape
     * RoundRectShape可以实现带有圆角的矩形
     *常用函数：
     * setBounds():指定当前ShapeDrawable在当前控件中的显示位置
     */
    private class ShapeView extends View {
        private ShapeDrawable shapeDrawable;
        public ShapeView(Context context) {
            super(context);
            init();
        }

        public ShapeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
        }
        private void init(){
            setLayerType(LAYER_TYPE_SOFTWARE,null);
            shapeDrawable = new ShapeDrawable(new RectShape());//生成ShapeDrawable实例，并将其形状定义为矩形
            shapeDrawable.setBounds(new Rect(50,50,200,100));//这里矩形位置为当前控件的位置，不是全屏幕的位置
            shapeDrawable.getPaint().setColor(Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            shapeDrawable.draw(canvas);
        }
    }
    private class PathShapeDrawable extends View{
        private ShapeDrawable shapeDrawable;
        public PathShapeDrawable(Context context) {
            super(context);
            init();
        }

        public PathShapeDrawable(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public PathShapeDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public PathShapeDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
        }
        private void init(){
            setLayerType(LAYER_TYPE_SOFTWARE,null);//禁用硬件加速
            Path path = new Path();
            path.moveTo(0,0);
            path.lineTo(100,0);
            path.lineTo(100,100);
            path.lineTo(0,100);
            path.close();
            shapeDrawable = new ShapeDrawable(new PathShape(path,100,200));//ShapeDrawable的高度和宽度被分为100份，200份
            shapeDrawable.getPaint().setColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            shapeDrawable.draw(canvas);
        }
    }
    //自定义Shape
    private class RegionShape extends Shape{

        private Region mRegion;
        public RegionShape(Region region){
            assert (region!= null);
            mRegion = region;
        }
        @Override
        public void draw(Canvas canvas, Paint paint) {
            RegionIterator iterator = new RegionIterator(mRegion);
            Rect r = new Rect();
            while (iterator.next(r)){
                canvas.drawRect(r,paint);
            }
        }
    }
    //自定义Shape的使用
    private class RegionShapeView extends View {
        private ShapeDrawable shapeDrawable;
        public RegionShapeView(Context context) {
            super(context);
            init();
        }

        public RegionShapeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public RegionShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public RegionShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
        }
        private void init(){
            Rect rect1 = new Rect(50,0,90,150);
            Rect rect2 = new Rect(0,50,250,100);
            Region region1 = new Region(rect1);
            Region region2 = new Region(rect2);
            //取两个区域的交集
            region1.op(region2, Region.Op.XOR);
            shapeDrawable =new ShapeDrawable(new RegionShape(region1));
            shapeDrawable.setBounds(new Rect(0,0,250,150));
            shapeDrawable.getPaint().setColor(Color.YELLOW);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            shapeDrawable.draw(canvas);
        }
    }

}
