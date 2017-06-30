package com.dtmob.android.network.interfaces;


import com.dtmob.android.network.NetCallback;

import java.util.Map;

/**
 * Created by andoop on 2016/11/1.
 */

public interface IBaseRequest<T> {
    String getUrl();
    T addParam(String key, String value);
    T addParams(Map<String, String> params);
    T addCallback(NetCallback callback);
    T url(String url);
    NetCallback getCallback();

}
