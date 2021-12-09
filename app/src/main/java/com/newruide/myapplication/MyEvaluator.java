package com.newruide.myapplication;

import android.animation.TypeEvaluator;

/**
 * ofInt(0,500)（定义动画区间）--> 插值器（返回当前数据进度）-->Evaluator(根据数值进度计算当前值)-->监听器返回（在AnimatorUpdateListener中返回）
 * Evaluator就是一个转换器，能够把小数进度转换为对应的数值位置
 * 只有定义动画的数值类型与Evaluator的返回值类型一样，才能使用这个Evaluator。
 * 比如，定义动画区间采用ofInt()函数，那么下例中的MyEvaluator适用于ofInt()函数
 * 下例中返回值增加了200，如果区间定义为(100,200),那么实际返回值区间应为（300，400）
 *
 * 结论：
 * 在插值器中，可以通过自定义插值器返回的数值进度来改变返回数据的位置；在Evaluator中，又可以通过改变数值进度对应的具体数值来改变数值的位置。
 *
 * ArgbEvaluator是用来实现颜色值过渡转换的
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        return (int)((endValue - startValue) *fraction +startValue +200);
    }
}
