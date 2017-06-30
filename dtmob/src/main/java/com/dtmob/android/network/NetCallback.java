package com.dtmob.android.network;


import com.dtmob.android.network.worker.NetError;

/**
 * Created by andoop on 2016/11/1.
 */

public interface NetCallback {
    void onSuccess(String datastr);
    void onFail(NetError netError);
}
