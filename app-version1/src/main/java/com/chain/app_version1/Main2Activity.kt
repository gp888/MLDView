package com.chain.app_version1

import android.os.Bundle
import android.util.Log
import com.chain.app_version1.A2
import com.chain.app_version1.C
import com.chain.app_version1.R
import com.chain.app_version1.UserInfo
import javax.inject.Inject

class Main2Activity : BaseActivity() {

    @Inject lateinit var a2: A2

    @Inject lateinit var c: C


    @Inject lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        a2.someThingA()
        c.doSomethingC()
        Log.e("DAGGER", userInfo.toString())
    }
}
