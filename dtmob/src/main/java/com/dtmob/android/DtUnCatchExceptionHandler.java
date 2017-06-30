package com.dtmob.android;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/26
* explain：自定义未捕获异常处理器
* * * * * * * * * * * * * * * * * * */

import android.content.Context;
import android.content.Intent;

class DtUnCatchExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
     DtUnCatchExceptionHandler(Context context, Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler) {
        mContext = context;
        this.defaultUncaughtExceptionHandler=defaultUncaughtExceptionHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        Intent intent=new Intent(mContext,DtMobService.class);
        intent.putExtra("command",DtMobService.SAVE_LOGS);
        mContext.startService(intent);
       defaultUncaughtExceptionHandler.uncaughtException(t,e);
    }
}
