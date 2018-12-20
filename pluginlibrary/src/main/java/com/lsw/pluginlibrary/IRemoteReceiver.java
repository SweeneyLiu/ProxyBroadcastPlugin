package com.lsw.pluginlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public interface IRemoteReceiver {
    public void onReceive(Context context, Intent intent);

    public void setProxy(BroadcastReceiver proxyReceiver);
}
