package com.gp.mldview;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class CameraRotateActivity extends AppCompatActivity {

    private boolean reverse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_rotate);
        ImageView view = (ImageView) findViewById(R.id.iv);
        assert view != null;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 计算中心点（这里是使用view的中心作为旋转的中心点）
                final float centerX = v.getWidth() / 2.0f;
                final float centerY = v.getHeight() / 2.0f;
                if (reverse) {
                    v.startAnimation(rotatePic(180, 359, centerX, centerY));
                    reverse = false;
                } else {
                    v.startAnimation(rotatePic(0, 180, centerX, centerY));
                    reverse = true;
                }
            }
        });
    }

    private Rotate3dAnimation rotatePic(float fromDegrees, float toDegress, float centerX, float centerY){
        //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
        final Rotate3dAnimation rotation = new Rotate3dAnimation(CameraRotateActivity.this, 0, 180, centerX, centerY, 0f, true);

        rotation.setDuration(3000);                         //设置动画时长
        rotation.setFillAfter(true);                        //保持旋转后效果
        rotation.setInterpolator(new LinearInterpolator());	//设置插值器
        return rotation;
    }
}
