package com.hencoder.hencoderpracticedraw6.practice;

import android.content.Context;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.hencoder.hencoderpracticedraw6.R;
import com.hencoder.hencoderpracticedraw6.Utils;

/**
 * Interpolator 就别练了，没什么好练的，Practice 和 Sample 的代码是一毛一样的。
 * 它的关键是理解，所以还是去看几眼实际效果吧。
 */
public class Practice07Interpolator extends LinearLayout {
    Spinner spinner;
    Button animateBt;
    ImageView imageView;

    Interpolator[] interpolators = new Interpolator[13];
    Path interpolatorPath;

    public Practice07Interpolator(Context context) {
        super(context);
    }

    public Practice07Interpolator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07Interpolator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        //画一个X轴（时间完成度）向右为正，y轴（动作完成度）向上为正的坐标。画出的图形是插值器的
        interpolatorPath = new Path();
        interpolatorPath.lineTo(0.25f, 0.25f);
        interpolatorPath.moveTo(0.25f, 1.5f);
        interpolatorPath.lineTo(1, 1);
        interpolators[0] = new AccelerateDecelerateInterpolator();//先加速再减速
        interpolators[1] = new LinearInterpolator();//匀速
        interpolators[2] = new AccelerateInterpolator();//持续加速
        interpolators[3] = new DecelerateInterpolator();//持续减速直到 0
        interpolators[4] = new AnticipateInterpolator();//先回拉一下再进行正常动画轨迹,弹射一样的先压缩再发射
        interpolators[5] = new OvershootInterpolator();//动画会超过目标值一些，然后再弹回来
        interpolators[6] = new AnticipateOvershootInterpolator();//开始前回拉，最后超过一些然后回弹
        interpolators[7] = new BounceInterpolator();//在目标值处弹跳。有点像玻璃球掉在地板上的效果
        interpolators[8] = new CycleInterpolator(0.5f);//这个也是一个正弦 / 余弦曲线。0.5为一个来回。
        interpolators[9] = PathInterpolatorCompat.create(interpolatorPath);//自己设置插值器
        interpolators[10] = new FastOutLinearInInterpolator();//加速运动，它和 AccelerateInterpolator 一样，它俩最主要的区别是 FastOutLinearInInterpolator 的初始阶段加速度比 AccelerateInterpolator 要快一些。
        interpolators[11] = new FastOutSlowInInterpolator();//先加速再减速
        interpolators[12] = new LinearOutSlowInInterpolator();//持续减速
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        spinner = (Spinner) findViewById(R.id.interpolatorSpinner);

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.animate()
                        .translationX(Utils.dpToPixel(150))
                        .setDuration(600)
                        .setInterpolator(interpolators[spinner.getSelectedItemPosition()])
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                imageView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageView.setTranslationX(0);
                                    }
                                }, 500);
                            }
                        });
            }
        });
    }
}