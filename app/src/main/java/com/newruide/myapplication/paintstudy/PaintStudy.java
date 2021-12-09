package com.newruide.myapplication.paintstudy;

/**
 * GPU:图形处理器，专门用于处理多媒体的计算、存储任务
 * 在API11之前没有GPU的概念，在API11之后，在程序集中加入了对GPU加速的支持，但是这一功能是默认关闭的
 * 在API14之后，硬件加速功能是默认开启的
 * 因此在11-13之中，可以显示的强制在进行图像计算时使用GPU而不使用CPU
 *
 *
 * 软件绘制与硬件加速的区别：
 * 在基于软件的绘制模型下，CPU主导绘图，视图按照两个步骤绘制：
 * （1）让View层次结构失效；（2）绘制View层次结构
 * 在基于硬件加速的绘制模式下，GPU主导绘图，视图按照三个步骤绘制：
 * （1）让View层次结构失效；（2）记录、更新显示列表；（3）绘制显示列表
 * 在GPU加速的时候，多了一项“记录更新显示列表”，
 * 把这些View的绘制函数指令记录在一个显示列表中，然
 * 后再读取显示列表中的绘制指令，调用OpenGL的相关函数完成绘制的。
 *
 * 若应用程序运行在API14以上版本，而恰好要用那些不支持硬件加速的函数，那么只能禁用硬件加速
 */

public class PaintStudy {
}
