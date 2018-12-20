package com.lsw.proxybroadcastplugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;


import com.lsw.pluginlibrary.AppConstants;
import com.lsw.pluginlibrary.MyClassLoaders;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {
    PluginItem pluginItem1;
    PluginItem pluginItem2;

    private static final String ACTION = "baobao2";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Utils.extractAssets(newBase, "plugin1.apk");
        Utils.extractAssets(newBase, "plugin2.apk");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pluginItem1 = generatePluginItem("plugin1.apk");
        pluginItem2 = generatePluginItem("plugin2.apk");
    }

    public void notifyReceiver1(View view) {
        Intent intent = new Intent(MainActivity.ACTION);
        intent.putExtra(AppConstants.EXTRA_PLUGIN_NAME, "plugin1.apk");
        intent.putExtra(AppConstants.EXTRA_CLASS, "com.lsw.plugin1.TestReceiver1");
        sendBroadcast(intent);
    }

    public void notifyReceiver2(View view) {
        Intent intent = new Intent(MainActivity.ACTION);
        intent.putExtra(AppConstants.EXTRA_PLUGIN_NAME, "plugin2.apk");
        intent.putExtra(AppConstants.EXTRA_CLASS, "com.lsw.plugin2.TestReceiver2");
        sendBroadcast(intent);
    }

    private PluginItem generatePluginItem(String apkName) {
        File file = getFileStreamPath(apkName);
        PluginItem item = new PluginItem();
        item.pluginPath = file.getAbsolutePath();
        item.packageInfo = DLUtils.getPackageInfo(this, item.pluginPath);

        String mDexPath = item.pluginPath;

        File dexOutputDir = this.getDir("dex", Context.MODE_PRIVATE);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        DexClassLoader dexClassLoader = new DexClassLoader(mDexPath,
                dexOutputPath, null, getClassLoader());

        MyClassLoaders.classLoaders.put(apkName, dexClassLoader);

        return item;
    }
}