package com.lsw.pluginlibrary;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BasePluginReceiver extends BroadcastReceiver implements IRemoteReceiver{

    public static final String TAG = "BasePluginReceiver";
    private BroadcastReceiver that;

    @Override
    public void setProxy(BroadcastReceiver proxyReceiver) {
        that = proxyReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
