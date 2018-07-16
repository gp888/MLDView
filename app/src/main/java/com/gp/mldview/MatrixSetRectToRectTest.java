package com.gp.mldview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MatrixSetRectToRectTest extends View {

    private static final String TAG = "MatrixSetRectToRectTest";

    private int mViewWidth, mViewHeight;

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mRectMatrix;         // 测试etRectToRect用的Matrix

    public MatrixSetRectToRectTest(Context context) {
        super(context);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.poly_test);
        mRectMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF src= new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight() );
        RectF dst = new RectF(0, 0, mViewWidth, mViewHeight );

        // 核心要点
        mRectMatrix.setRectToRect(src,dst, Matrix.ScaleToFit.CENTER);

        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mRectMatrix, new Paint());
    }
}
