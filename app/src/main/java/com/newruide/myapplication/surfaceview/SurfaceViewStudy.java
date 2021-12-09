package com.newruide.myapplication.surfaceview;

public class SurfaceViewStudy {
    /**
     * Android屏幕刷新时间是16ms，如果View在16ms完成所需执行的绘图操作，那么视觉上，界面是流畅的，否则会出现卡顿
     * 为了解决这个问题，Android引入了SurfaceView，它在两个方面该进了View的绘图操作：
     * 1、使用双缓冲技术；
     * 2、自带画布，支持在子线程中更新画布内容；
     *
     * 所谓的双缓冲技术，就是多家一块缓冲话不要，当需要执行绘图操作
     * 先在缓冲画布上绘制，绘制好后直接将缓冲画布上的内容更新到主画布。
     * 这样，在屏幕更新时，只需要把缓冲画布上的内容更新到主画布上。
     * View和SurfaceView都有各自的应用场景
     * 当界面需要被动更新，用View比较好；
     * 当界面需要主动更新，需要用SurfaceView控制；
     * 当界面绘制需要频繁刷新或者刷新时候数据处理量比较大，就应该用SurfaceView来实现。
     *
     *
     * SurfaceView派生于View，需要注意的是，View中的所有方法都是在主线程中执行的，因此，
     * 如果重写的方法来自View，那么也是在主线程中执行的。
     *
     * */
}
