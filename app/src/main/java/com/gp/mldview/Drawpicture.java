package com.gp.mldview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;

import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * drawPicture,drawBitmap
 * Created by guoping on 2018/4/2.
 */

public class Drawpicture extends View {
    private Paint mPaint = new Paint();

    private Picture mPicture = new Picture();
    DisplayMetrics dm2 = getResources().getDisplayMetrics();

    public Drawpicture(Context context) {
        super(context);
        initPaint();
    }

    public Drawpicture(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        recording();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPicture.draw(canvas);//比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用

//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));

//        PictureDrawable drawable = new PictureDrawable(mPicture);
//            // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.setBounds(0,0,250,mPicture.getHeight());
//        drawable.draw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.paidaxing);
//        canvas.drawBitmap(bitmap,new Matrix(),new Paint());

//        canvas.drawBitmap(bitmap,200,500,new Paint());//与坐标原点的距离

        canvas.translate(dm2.widthPixels/2,dm2.heightPixels/2);
        // 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2);
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0,0,200,400);
        canvas.drawBitmap(bitmap,src,dst,null);
    }

    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100, mPaint);

        mPicture.endRecording();
    }

}
