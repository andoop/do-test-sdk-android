package com.dtmob.android;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/26
* explain：DoTest入口
* * * * * * * * * * * * * * * * * * */

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DtMob {


    private static DtMob INSTANCE;

    private DtMob() {
    }

    public static DtMob newInstance() {

        if (INSTANCE == null) {
            synchronized (DtMob.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DtMob();
                }
            }

        }
        return INSTANCE;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        //清除log
        clear_logs();
        //开启服务
        Log.e("----->" + "DtMob", "init:" + "开启服务");
        context.startService(new Intent(context,DtMobService.class));
        //注册unCatchExceptionHandler
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new DtUnCatchExceptionHandler(context,defaultUncaughtExceptionHandler));
    }

    /**
     * 清除缓存中log 指main缓存区
     */
    public void clear_logs() {
        LgsTool.clear();
    }

    /**
     * 退出，这里会统计所有log到本地文件
     */
    public void onExit() {
        save_all_logs();
    }

    /**
     * 保存log到本地
     */
    private void save_all_logs() {
        LgsTool.save();
    }
}
