package com.dtmob.android;

import android.os.SystemClock;
import android.util.Log;

import com.dtmob.android.network.Api;
import com.dtmob.android.network.NetCallback;
import com.dtmob.android.network.NetWorkExcutor;
import com.dtmob.android.network.request.DefaultPostRequest;
import com.dtmob.android.network.worker.NetError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by domob on 2017/6/29.
 */
 class DtNetWorkController {


    private static DtNetWorkController INSTANCE;

    private DtNetWorkController() {
    }

     static DtNetWorkController newInstance() {

        if (INSTANCE == null) {
            synchronized (DtNetWorkController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DtNetWorkController();
                }
            }

        }
        return INSTANCE;
    }


    /**
     * 向服务端添加一条log
     * @param appId
     * @param userId
     * @param data
     */
     void add_log(String appId, String userId, List<String> data) {

         //添加参数
         Map<String,String> params=new HashMap<>();
         params.put("app_id",appId);
         params.put("user_id",userId);
         params.put("log_time",String.valueOf(System.currentTimeMillis()));
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("[");
         for(String log:data) {
             stringBuilder.append("\"");
             stringBuilder.append(log);
             stringBuilder.append("\"");
             stringBuilder.append(",");
         }
         stringBuilder.append("\"end\"]");
         params.put("log_json",stringBuilder.toString());
         postRequest(Api.add_log,params);
    }

    private void postRequest(String url,Map<String,String> params){
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(url)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        Log.e("----->" + "net", "onSuccess:" + datastr);
                    }

                    @Override
                    public void onFail(NetError netError) {
                        Log.e("----->" + "net", "onFail:" + netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
    }
}
