package com.newruide.myapplication;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

public class OfObject {

   public Animator doAnimator(){
       ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(),new Character('A'),new Character('Z'));
       valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
       return valueAnimator;
   }

   class CharEvaluator implements TypeEvaluator<Character>{
       @Override
       public Character evaluate(float fraction, Character startValue, Character endValue) {
           int startInt  = (int)startValue;
           int endInt = (int)endValue;
           int curInt = (int)(startInt +fraction *(endInt - startInt));
           char curChar = (char)curInt;
           return curChar;
       }
   }
}
