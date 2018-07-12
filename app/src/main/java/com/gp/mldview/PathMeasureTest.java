package com.gp.mldview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PathMeasureTest extends View {

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    int mWidth,mHeight;

    private Paint mPaint;

    public PathMeasureTest(Context context) {
        super(context);
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initPaint();
    }

    public PathMeasureTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arraw, options);
        mMatrix = new Matrix();
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

        canvas.translate(mWidth / 2, mHeight / 2);      // 平移坐标系

        Path path = new Path();                                 // 创建 Path

        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形,圆的起点是(半径,0)

        PathMeasure measure = new PathMeasure(path, false);     // 创建 PathMeasure

        currentValue += 0.005;                                  // 计算当前的位置在总长度上的比例[0,1]
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        measure.getPosTan(measure.getLength() * currentValue, pos, tan);        // 获取当前位置的坐标以及趋势
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI); // 计算图片旋转角度
//        Log.e("TAG", "position---->(" + pos[0] + "," + pos[1] + ")degree---->("+degrees+ ")tangent--->(" + tan[0] + "," + tan[1] + ")");
//        mMatrix.reset();// 重置Matrix
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);   // 旋转图片
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);   // 将图片绘制中心调整到与当前点重合


        // 获取当前位置的坐标以及趋势的矩阵
        measure.getMatrix(measure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

        canvas.drawPath(path, mPaint);                                   // 绘制 Path
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);                     // 绘制箭头

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
