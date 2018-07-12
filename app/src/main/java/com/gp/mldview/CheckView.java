package com.gp.mldview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by guoping on 2018/4/2.
 */

public class CheckView extends View {
    Paint mPaint = new Paint();
    private int bitmapheight;
    private int x;
    private Bitmap bitmapCheck;
    Handler handler;
    int width,height;
    boolean isCancel = true;

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        bitmapCheck = BitmapFactory.decodeResource(getResources(), R.drawable.check);
        bitmapheight = bitmapCheck.getHeight();
//        x = -bitmapheight;
        x = bitmapheight * 12;

        mPaint.setColor(Color.LTGRAY);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (isCancel) {
                    handler.sendMessageDelayed(handler.obtainMessage(0), 500/13);
                    x -= bitmapheight;
                    invalidate();
                } else {
                    handler.sendMessageDelayed(handler.obtainMessage(0), 500/13);
                    x += bitmapheight;
                    invalidate();
                }
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,height/2);
        canvas.drawCircle(0, 0, 100, mPaint);

        Rect src = new Rect(x,0, x + bitmapheight, bitmapheight);

        Rect dst = new Rect(-100,-100,100,100);

        canvas.drawBitmap(bitmapCheck, src, dst,null);

        handler.sendMessageDelayed(handler.obtainMessage(0), 500);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
}
