package com.newruide.myapplication.measureandlayout;

/**
 * View 与 ViewGroup基本相同
 * 只是ViewGroup中不仅要绘制绘制自身，还要绘制其中的子控件
 * 绘制流程分为三步：
 * 测量、布局、绘制
 * 分别对应
 * onMeasure():测量当前控件的大小，为正式的布局提供建议
 * onLayout()：使用layout()函数为所有子控件进行布局
 * onDraw()：根据布局的位置进行绘图
 *
 * 布局绘画涉及两个过程：
 * 测量过程和布局过程。
 * 测量过程通过measure()函数实现。
 */
public class MeasureAndLayout {
}
