package com.gp.mldview.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;

/**
 * Created by guoping on 2018/4/2.
 */

public class BingTu extends View implements ViewTreeObserver.OnGlobalLayoutListener{

    private Paint mPaint = new Paint();
    private int mWidth, mHeight;
    private List<PieData> mData;
    private float totalValue = 0f;

    // 饼状图初始绘制角度
    private float mStartAngle = 0f;

    public BingTu(Context context) {
        this(context, null);
    }

    public BingTu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    public void setData(List<PieData> mData ){
        this.mData = mData;
        initData(mData);
        invalidate();

    }

    private void initData(List<PieData> mData){
        if (null == mData || mData.size() == 0)
            return;

        for(int i = 0; i < mData.size(); i++) {
            totalValue += mData.get(i).getValue();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mData == null && mData.size() == 0) {
            return;
        }
        float currentStartAngle = mStartAngle;                    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);          // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rectF = new RectF(-r, -r, r, r);                     // 饼状图绘制区域


        for(int i = 0; i < mData.size(); i++) {
            float percent = mData.get(i).getValue() / totalValue;
            mPaint.setColor(mData.get(i).getColor());
            canvas.drawArc(rectF, currentStartAngle, 360 * percent, true, mPaint);
            currentStartAngle += 360 * percent;
        }
    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }


    @Override
    public void onGlobalLayout() {

    }
}
