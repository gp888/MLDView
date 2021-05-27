package com.gp.mldview.languagelib;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.e(TAG, "attachBaseContext");
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
        Locale locale = getResources().getConfiguration().locale;
        Log.e(TAG, "attachBaseContext locale " + locale  + "  getLanguageLocale " + MultiLanguageUtil.getInstance().getLanguageLocale(this));

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
