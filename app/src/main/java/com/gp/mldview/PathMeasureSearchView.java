package com.gp.mldview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自己尝试
 */
public class PathMeasureSearchView extends View {

    private Paint mPaint;
    private int mWidth,mHeight;

    private int state = 0; //0初始状态，1准备搜索，2正在搜索，3结束搜索
    private float currentValue = 0;
    Path path;
    PathMeasure pathMeasure;
    float cf = (float) (2* Math.PI * 50);

    public PathMeasureSearchView(Context context) {
        super(context);
    }

    public PathMeasureSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       initPaint();
    }

    public PathMeasureSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mPaint = new Paint();             // 创建画笔
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(15);              // 边框宽度 - 10
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        path = new Path();
        path.addCircle(0, 0, 50, Path.Direction.CW);
        path.moveTo((float) (50/ Math.sqrt(2d)), (float) (50/ Math.sqrt(2d)));
        path.lineTo((float) (100/ Math.sqrt(2d)), (float) (100/ Math.sqrt(2d)));

        pathMeasure = new PathMeasure(path, false);
    }

    public void setState(int state) {
        this.state = state;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
//       if (state == 0) {
//           canvas.drawPath(path, mPaint);
//
////            Path path1 = new Path();
////            path1.addCircle(0, 0, 100, Path.Direction.CW);
////            canvas.drawPath(path1, mPaint1);
//       } else if (state == 1) {
//           Path path1 = new Path();
//           currentValue += 0.005;
//
//           mPaint.setColor(Color.BLACK);
//           canvas.drawPath(path, mPaint);
//
//           float startD = ((float)(Math.PI * 50 / 4)) + 7f;
//           float endD = cf * currentValue;
//
//           float r = startD / cf;
//           if (currentValue >= (1 - r) && currentValue < 1) {
//              float startD1 = 0;
//              float endD1 = startD * (currentValue - r);
//               pathMeasure.getSegment(startD1, endD1, path1, true);
//           } else {
//               pathMeasure.getSegment(startD, endD, path1, true);
//           }
//           mPaint.setColor(Color.WHITE);
//           canvas.drawPath(path1, mPaint);
//
//           if (currentValue < 1) {
//               invalidate();
//           }
//
//             else {
//               pathMeasure.nextContour();
//               pathMeasure.getSegment(0, currentValue - 1, path1, true);
//           }
//       }

        Path path_srarch = new Path();
        RectF oval1 = new RectF(-50, -50, 50, 50);          // 放大镜圆环
        path_srarch.addArc(oval1, 45, 359.9f);
        PathMeasure mMeasure = new PathMeasure();
        mMeasure.setPath(path_srarch, false);
        Path dst = new Path();
        mMeasure.getSegment(mMeasure.getLength(), mMeasure.getLength(), dst, true);
        canvas.drawPath(dst, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
