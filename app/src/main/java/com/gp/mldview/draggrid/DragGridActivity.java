package com.gp.mldview.draggrid;


import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.gp.mldview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖拽交换位置的gridview
 */
public class DragGridActivity extends Activity {
    private List<String> strList;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draggrid);
        initData();
        initView();
    }

    public void initData(){
        strList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            strList.add("Channel " + i);
        }
    }

    private void initView() {
        gridView = (GridView)findViewById(R.id.drag_grid_view);
        GridViewAdapter adapter = new GridViewAdapter(this, strList);
        gridView.setAdapter(adapter);
    }
}

