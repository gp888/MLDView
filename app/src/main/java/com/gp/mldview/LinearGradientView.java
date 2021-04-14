package com.gp.mldview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class LinearGradientView extends View {
    Paint mPaint;
    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient shader = new LinearGradient(0, 0, 400, 0, Color.parseColor("#F9374B"), Color.parseColor("#F86648"), Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawRect(0, 0, 400, 400, mPaint);
    }
}
