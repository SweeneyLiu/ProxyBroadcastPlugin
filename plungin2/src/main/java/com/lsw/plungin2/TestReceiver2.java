package com.lsw.plungin2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lsw.pluginlibrary.BasePluginReceiver;


public class TestReceiver2 extends BasePluginReceiver {

    private static final String TAG = "TestReceiver2";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "TestReceiver2 onReceive");
    }
}
