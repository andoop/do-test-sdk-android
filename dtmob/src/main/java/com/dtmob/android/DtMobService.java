package com.dtmob.android;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dtmob.android.tools.SPUtils;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/27
* explain：处在于其他进程的服务
* * * * * * * * * * * * * * * * * * */

public class DtMobService extends Service {
    public static final int CLEAR_LOGS=1;
    public static final int SAVE_LOGS=2;

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
        int command = intent.getIntExtra("command", 0);
        switch (command){
            case CLEAR_LOGS:
                //清除缓存区log
                Log.e("----->" + "DtMobService", "onStartCommand:" + "清除log");
                LgsTool.clear();
                break;
            case SAVE_LOGS:
                LgsTool.save(getApplicationContext());
                break;

        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
