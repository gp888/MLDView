package com.chain.app_version1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    A2 a2;

    @Inject
    C c;


    @Inject
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        a2.someThingA();
        c.doSomethingC();

        userInfo.setAge("111");
        Log.e("DAGGER", userInfo.toString());
    }
}
