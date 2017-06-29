package com.dtmob.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/27
* explain：处在于其他进程的服务
* * * * * * * * * * * * * * * * * * */

public class DtMobService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("----->" + "DtMobService", "onCreate:");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("----->" + "DtMobService", "onDestroy:" + "保存log");
        //保存log
        LgsTool.save();
        super.onDestroy();
    }
}
