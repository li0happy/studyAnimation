package com.newruide.myapplication.canvasstudy;

public class CanvasAndLayer {
    /**
     * 一、
     * onDraw()、dispatchDraw()函数在传入的参数中有一个Canvas对象，
     * 这个Canvas对象是View中的Canvas对象，利用这个Canvas对象绘图，效果会直接反应在View中。
     * onDraw()函数用于绘制视图自身；dispatchDraw()函数用于绘制子视图。
     * 无论是View还是ViewGroup，对这两个函数的调用顺序都是onDraw->dispatchDraw
     *
     * 二、
     *图层（Layer）:每次调用canvas.drawXX系列函数，都会生成一个透明的图层来绘制这个图形
     * 画布(bitmap)：画布分为两种，一种是View的原始画布，是通过onDraw函数传入的，控件的背景就花在这块画布上，
     * 另一种是人造画布，通过saveLayer()、new Canvas(bitmap)等人为的新建一块画布。
     * 一旦调用saveLayer()新建一块画布，以后所有 Draw函数所画的图像都是画在这块画布上的，只有在调用restore()
     * restoreToCount()函数以后，才会返回到原始画布上进行绘制。
     * Canvas:画布的表现形式，代码中，Canvas得生成方式就一种，new Canvas(bitmap)，即只能通过Bitmap生成，
     * 无论是原始画布还是人造画布，所有的画布最后都是通过 Canvas画到Bitmap上的。
     *
     *
     *三、Canvas中有几个save函数
     *
     */
}
