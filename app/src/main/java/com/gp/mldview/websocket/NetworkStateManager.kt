package com.gp.mldview.websocket

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.NetworkUtils

object NetworkStateManager : CoroutineScope by MainScope() {
    private const val TAG = "NetworkStateManager"
    private val _networkState = MutableLiveData(false)
    val networkState: LiveData<Boolean> = _networkState

    @JvmStatic
    fun init(context: Context) {
        _networkState.postValue(NetworkUtils.isConnected())
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(NetworkStateReceiver(), filter)
    }

    class NetworkStateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (context == null || intent == null) {
                return
            }
            val isConnected =
                intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false).not()
            Log.d(TAG, "network state changed, is connected: $isConnected")
            launch {
                _networkState.postValue(isConnected)
            }
        }
    }
}