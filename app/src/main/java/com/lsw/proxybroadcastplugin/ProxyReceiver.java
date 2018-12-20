package com.lsw.proxybroadcastplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.lsw.pluginlibrary.AppConstants;
import com.lsw.pluginlibrary.IRemoteReceiver;
import com.lsw.pluginlibrary.MyClassLoaders;

import java.lang.reflect.Constructor;

public class ProxyReceiver extends BroadcastReceiver {
    
    private static final String TAG = "ProxyService";

    private String mClass;
    private String pluginName;
    private IRemoteReceiver mRemoteReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, TAG + " onReceive");

        pluginName = intent.getStringExtra(AppConstants.EXTRA_PLUGIN_NAME);
        mClass = intent.getStringExtra(AppConstants.EXTRA_CLASS);

        try {
            //反射出插件的Receiver对象
            Class<?> localClass = MyClassLoaders.classLoaders.get(pluginName).loadClass(mClass);
            Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});

            mRemoteReceiver = (IRemoteReceiver) instance;
            mRemoteReceiver.setProxy(this);
            mRemoteReceiver.onReceive(context, intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

