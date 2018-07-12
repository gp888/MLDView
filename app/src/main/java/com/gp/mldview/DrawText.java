package com.gp.mldview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by guoping on 2018/4/2.
 */

public class DrawText extends View {
    int width,height;
    Paint textPaint;

    public DrawText(Context context) {
        super(context);
       initPaint();
    }

    public DrawText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        textPaint = new Paint();          // 创建画笔
        textPaint.setColor(Color.BLACK);        // 设置颜色
        textPaint.setStyle(Paint.Style.FILL);   // 设置样式
        textPaint.setTextSize(50);              // 设置字体大小
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        String str = "ABCDEFG";
//        // 参数分别为 (文本 基线x 基线y 画笔)
//        canvas.drawText(str,1, 3, 200,500,textPaint);

        String str = "SLOOP";

        canvas.drawPosText(str,new float[]{
                100,100,    // 第一个字符位置
                200,200,    // 第二个字符位置
                300,300,    // ...
                400,400,
                500,500
        },textPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
}
