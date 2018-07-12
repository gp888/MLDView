package com.gp.mldview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PathView extends View{

    private Paint mPaint;

    int mWidth,mHeight;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);              // 边框宽度 - 10
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
//        Path path = new Path();                     // 创建Path
//        path.lineTo(200, 200);                      // lineTo
//        path.lineTo(200,0);
//        canvas.drawPath(path, mPaint);



//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        Path path = new Path();
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        path.setLastPoint(-300,300);                // <-- 重置最后一个点的位置
//        canvas.drawPath(path,mPaint);




//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        Path path = new Path();
//        path.addRect(-200,-200,200,200, Path.Direction.CCW);
//        path.setLastPoint(-300,300);                // <-- 重置最后一个点的位置
//        canvas.drawPath(path, mPaint);



//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//
//        Path path = new Path();
//        Path src = new Path();
//
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        src.addCircle(0,0,100, Path.Direction.CW);
//
//        path.addPath(src,0,200);
//
//        mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
//        canvas.drawPath(path,mPaint);


//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//
//        Path path = new Path();
//        path.lineTo(100,100);
//
//        RectF oval = new RectF(0,0,300,300);
//
////        path.addArc(oval,0,270);
//        // path.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价
//
//
////        path.arcTo(oval,0,270);
//         path.arcTo(oval,0,270,false);
//
//        canvas.drawPath(path,mPaint);




//        mPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充
//        canvas.translate(mWidth / 2, mHeight / 2);          // 移动画布(坐标系)
//        Path path = new Path();                                     // 创建Path
//        path.addRect(-200,-200,200,200, Path.Direction.CCW);         // 给Path中添加一个矩形
//        path.setFillType(Path.FillType.EVEN_ODD);                   // 设置Path填充模式为 奇偶规则
////        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);            // 反奇偶规则
//        canvas.drawPath(path, mPaint);



//        mPaint.setStyle(Paint.Style.FILL);                   // 设置画笔模式为填充
//        canvas.translate(mWidth / 2, mHeight / 2);          // 移动画布(坐系)
//        Path path = new Path();                                     // 创建Path
//
//        // 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
////        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);
//        // 添加大正方形
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        path.setFillType(Path.FillType.INVERSE_WINDING);                    // 设置Path填充模式为非零环绕规则
//        canvas.drawPath(path, mPaint);                       // 绘制Path



//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.translate(mWidth / 2, mHeight / 2);
//
//        Path path1 = new Path();
//        Path path2 = new Path();
//        Path path3 = new Path();
//        Path path4 = new Path();
//
//        path1.addCircle(0, 0, 200, Path.Direction.CW);
//        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
//        path3.addCircle(0, -100, 100, Path.Direction.CW);
//        path4.addCircle(0, 100, 100, Path.Direction.CCW);
//
//
//        path1.op(path2, Path.Op.DIFFERENCE);
//        path1.op(path3, Path.Op.UNION);
//        path1.op(path4, Path.Op.DIFFERENCE);
//
//        canvas.drawPath(path1, mPaint);



//        mPaint.setStyle(Paint.Style.FILL);
//        int x = 80;
//        int r = 100;
//
//        canvas.translate(280,0);
//
//        Path path1 = new Path();
//        Path path2 = new Path();
//        Path pathOpResult = new Path();
//
//        path1.addCircle(-x, 0, r, Path.Direction.CW);
//        path2.addCircle(x, 0, r, Path.Direction.CW);
//
//        pathOpResult.op(path1,path2, Path.Op.DIFFERENCE);
//        canvas.translate(0, 200);
//        canvas.drawText("DIFFERENCE", 240,0, mPaint);
//        canvas.drawPath(pathOpResult, mPaint);
//
//        pathOpResult.op(path1,path2, Path.Op.REVERSE_DIFFERENCE);
//        canvas.translate(0, 300);
//        canvas.drawText("REVERSE_DIFFERENCE", 240,0,mPaint);
//        canvas.drawPath(pathOpResult,mPaint);
//
//        pathOpResult.op(path1,path2, Path.Op.INTERSECT);
//        canvas.translate(0, 300);
//        canvas.drawText("INTERSECT", 240,0,mPaint);
//        canvas.drawPath(pathOpResult,mPaint);
//
//        pathOpResult.op(path1,path2, Path.Op.UNION);
//        canvas.translate(0, 300);
//        canvas.drawText("UNION", 240,0,mPaint);
//        canvas.drawPath(pathOpResult,mPaint);
//
//        pathOpResult.op(path1,path2, Path.Op.XOR);
//        canvas.translate(0, 300);
//        canvas.drawText("XOR", 240,0,mPaint);
//        canvas.drawPath(pathOpResult,mPaint);


//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.translate(mWidth/2,mHeight/2);
//
//        RectF rect1 = new RectF();              // 存放测量结果的矩形
//
//        Path path = new Path();                 // 创建Path并添加一些内容
//        path.lineTo(100,-50);
//        path.lineTo(100,50);
//        path.close();
//        path.addCircle(-100,0,100, Path.Direction.CW);
//
//        path.computeBounds(rect1,true);         // 测量Path
//
//        canvas.drawPath(path, mPaint);    // 绘制Path
//
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.RED);
//        mPaint.setStrokeWidth(1);
//        canvas.drawRect(rect1, mPaint);   // 绘制边界




//        canvas.translate(mWidth/2,mHeight/2);
//
//        Path path = new Path();
//
//        path.lineTo(0,200);
//        path.lineTo(200,200);
//        path.lineTo(200,0);
//
//        PathMeasure measure1 = new PathMeasure(path,false);
//        PathMeasure measure2 = new PathMeasure(path,true);
//
//        Log.e("TAG", "forceClosed=false---->"+measure1.getLength());
//        Log.e("TAG", "forceClosed=true----->"+measure2.getLength());
//
//        canvas.drawPath(path, mPaint);




        canvas.translate(mWidth / 2, mHeight / 2);          // 平移坐标系

        Path path = new Path();                                     // 创建Path并添加了一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        Path dst = new Path();                                      // 创建用于存储截取后内容的 Path
        dst.addCircle(0,0, 20, Path.Direction.CCW);

        PathMeasure measure = new PathMeasure(path, false);         // 将 Path 与 PathMeasure 关联

        // 截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
        measure.getSegment(200, 600, dst, false);

        canvas.drawPath(dst, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
