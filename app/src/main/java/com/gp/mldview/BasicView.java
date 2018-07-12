package com.gp.mldview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by guoping on 2018/4/2.
 */

public class BasicView extends View {
    private Paint mPaint = new Paint();

    public BasicView(Context context) {
        super(context);
        initPaint();
    }

    public BasicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPoint(200, 200, mPaint);
//        canvas.drawPoints(new float[] {
//                500,500,
//                500,600,
//                500,700
//        }, mPaint);
//
//
//        canvas.drawLine(300,300,500,600,mPaint);    // 在坐标(300,300)(500,600)之间绘制一条直线
//        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
//                100,200,200,200,
//                100,300,200,300
//        },mPaint);
//
//
//        // 第一种
//        canvas.drawRect(100,100,800,400,mPaint);
//        // 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect,mPaint);
//        // 第三种
//        RectF rectF0 = new RectF(100,100,800,400);
//        canvas.drawRect(rectF0,mPaint);
//
//
//
//        // 第一种
//        RectF rectF1 = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectF1,30,30,mPaint);
//        // 第二种
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawRoundRect(100,100,800,400,30,30,mPaint);
//        }
//
//
//        // 矩形
//        RectF rectF2 = new RectF(100,100,800,400);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2, mPaint);
//
//        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRoundRect(rectF2,700,400,mPaint);
//
//
//
//
//        // 第一种
//        RectF rectF3 = new RectF(100,100,800,400);
//        canvas.drawOval(rectF3,mPaint);
//
//        // 第二种
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawOval(100,100,800,400,mPaint);
//        }
//
//
//
//        canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。
//
//
//
//
//        RectF rectF4 = new RectF(100,100,800,400);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF4,mPaint);
//
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF4,0,90,false,mPaint);
//
//        //-------------------------------------
//
//        RectF rectF5 = new RectF(100,600,800,900);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF5,mPaint);
//
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF5,0,90,true,mPaint);
//
//
//        //正圆的弧
//        RectF rectF6 = new RectF(100,100,600,600);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF6,mPaint);
//
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF6,0,90,false,mPaint);
//
//        //-------------------------------------
//
//        RectF rectF7 = new RectF(100,700,600,1200);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF7,mPaint);
//
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF7,0,90,true,mPaint);


//        mPaint
//        STROKE                //描边
//        FILL                  //填充
//        FILL_AND_STROKE       //描边加填充


//// 在坐标原点绘制一个黑色圆形
//        mPaint.setColor(Color.BLACK);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//
//// 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);


        DisplayMetrics dm2 = getResources().getDisplayMetrics();
//        // 将坐标系原点移动到画布正中心
//        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//
////        canvas.scale(0.5f,0.5f);                // 画布缩放
////        canvas.scale(0.5f,0.5f,200,0);          // 画布缩放  <-- 缩放中心向右偏移了200个单位
////        canvas.scale(-0.5f,-0.5f);          // 画布缩放
////        canvas.scale(0.5f,-0.5f,200,0);
//        mPaint.setColor(Color.WHITE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);


//        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);
//
//        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域
//        mPaint.setStyle(Paint.Style.STROKE);  //
//        for (int i=0; i<=20; i++)
//        {
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRect(rect,mPaint);
//        }


//        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);
//
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//
////        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
//        canvas.rotate(180, 200, 0);
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);


//        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(0,0,400,mPaint);          // 绘制两个圆形
//        canvas.drawCircle(0,0,380,mPaint);
//
//        for (int i=0; i<=360; i+=10){               // 绘制圆形之间的连接线
//            canvas.drawLine(0,380,0,400,mPaint);
//            canvas.rotate(10);
//        }


//        错切
//        X = x + sx * y
//        Y = sy * x + y
//        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);
//
//        RectF rect = new RectF(0,0,200,200);   // 矩形区域
//
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//
//        canvas.skew(1,0);                       // 水平错切 <- 45度
//
//        mPaint.setColor(Color.WHITE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);

        canvas.translate(dm2.widthPixels / 2, dm2.heightPixels / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 水平错切
        canvas.skew(0,1);                       // 垂直错切

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
}
