package com.gp.mldview;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gp.mldview.draggrid.DragGridActivity;
import com.gp.mldview.ether.Web3jActivity;
import com.gp.mldview.guideview.GuideDemoActivity;
import com.gp.mldview.loadingview.LeafLoadingActivity;
import com.gp.mldview.pie.PieActivity;

public class MainActivity extends AppCompatActivity implements View.OnLayoutChangeListener{


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.guide:
                    startActivity(new Intent(MainActivity.this, GuideDemoActivity.class));
                    break;
                case R.id.drag_grid:
                    startActivity(new Intent(MainActivity.this, DragGridActivity.class));
                    break;
                case R.id.pie:
                    startActivity(new Intent(MainActivity.this, PieActivity.class));
                    break;
                case R.id.search:
                    startActivity(new Intent(MainActivity.this, SearchViewActivity.class));
                    break;
                case R.id.mysearch:
                    startActivity(new Intent(MainActivity.this, MySearchViewActivity.class));
                    break;
                case R.id.setPoly:
                    startActivity(new Intent(MainActivity.this, SetPolyToPolyActivity.class));
                    break;
                case R.id.rotateAni:
                    startActivity(new Intent(MainActivity.this, CameraRotateActivity.class));
                    break;
                case R.id.leafload:
                    startActivity(new Intent(MainActivity.this, LeafLoadingActivity.class));
                case R.id.rxjava2:
//                    startActivity(new Intent(MainActivity.this, Rxjava2Activity.class));
//                    startActivity(new Intent(MainActivity.this, SpannableActivity.class));
                    startActivity(new Intent(MainActivity.this, Web3jActivity.class));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.guide).setOnClickListener(listener);
        findViewById(R.id.drag_grid).setOnClickListener(listener);
        findViewById(R.id.pie).setOnClickListener(listener);
        findViewById(R.id.search).setOnClickListener(listener);
        findViewById(R.id.mysearch).setOnClickListener(listener);
        findViewById(R.id.setPoly).setOnClickListener(listener);
        findViewById(R.id.rotateAni).setOnClickListener(listener);
        findViewById(R.id.leafload).setOnClickListener(listener);
        findViewById(R.id.rxjava2).setOnClickListener(listener);



        View decorView = getWindow().getDecorView();
        FrameLayout contentParent = (FrameLayout) decorView.findViewById(android.R.id.content);
        TextView x = new TextView(this);
        x.setText("这是一个TextView\n通过DecorView将其绘制到了Activity上层");
        x.setGravity(Gravity.CENTER);
        x.setBackgroundColor(Color.GRAY);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        lp.gravity = Gravity.BOTTOM;
        x.setLayoutParams(lp);
        contentParent.addView(x);
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

    }
}
