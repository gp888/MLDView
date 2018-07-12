package com.gp.mldview.pie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gp.mldview.R;

import java.util.ArrayList;
import java.util.List;

public class PieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        BingTu bt = findViewById(R.id.bt);
        List<PieData> mData = new ArrayList<>();
        PieData data1 = new PieData("1", 32.5f);
        data1.setColor(Color.BLUE);
        mData.add(data1);

        PieData data2 = new PieData("1", 188.5f);
        data2.setColor(Color.RED);
        mData.add(data2);

        PieData data3 = new PieData("1", 2338.5f);
        data3.setColor(Color.DKGRAY);
        mData.add(data3);

        PieData data4 = new PieData("1", 500f);
        data4.setColor(Color.argb(255, 255, 200, 10));
        mData.add(data4);

        bt.setData(mData);
    }
}
