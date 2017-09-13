package com.hencoder.hencoderpracticedraw6.practice.practice08;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw6.Utils;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ProgressView extends View {
    private float progress = 0;
    private float radius = Utils.dpToPixel(80);


    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    private int centerX, centerY;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dpToPixel(15
        ));
        paint.setStrokeCap(Paint.Cap.ROUND);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        Path path = new Path();
        path.addArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius, 135, progress * 2.7f);
        canvas.drawPath(path, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(Utils.dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((int) progress + "%", centerX, centerY - (paint.ascent() - paint.descent()) / 2, paint);
    }
}
