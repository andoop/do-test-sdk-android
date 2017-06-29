package com.andoop.android.dotest;

import android.app.Application;

import com.dtmob.android.DtMob;

/**
 * Created by domob on 2017/6/26.
 */

public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DtMob.newInstance().init(this);
    }
}
