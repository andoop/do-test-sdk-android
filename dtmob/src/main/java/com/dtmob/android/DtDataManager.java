package com.dtmob.android;
/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/29
* explain：数据管理器
* * * * * * * * * * * * * * * * * * */

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.dtmob.android.tools.FileUtils;
import com.dtmob.android.tools.SPUtils;

import java.io.File;
import java.util.List;

class DtDataManager {


    private static DtDataManager INSTANCE;
    //日志文件全路径
    private String log_file_path;
    private DtDataManager() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "00dt.txt");
        log_file_path = file.getAbsolutePath();
        init_Log_File();
    }

    static DtDataManager newInstance() {

        if (INSTANCE == null) {
            synchronized (DtDataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DtDataManager();
                }
            }

        }
        return INSTANCE;
    }

    /**
     * 获取日志文件路径
     *
     * @return
     */
    String get_log_file_path() {
        return log_file_path;
    }

    /**
     * 清除日志文件内容
     */
    void init_Log_File() {
        if (TextUtils.isEmpty(log_file_path))
            return;
        FileUtils.init_File(log_file_path);
    }

    /**
     * 汇报本地log
     */
    void do_rep_local_logs(final Context context) {

        if (TextUtils.isEmpty(log_file_path))
            return;

        FileUtils.readTextFromFileOfListFormat(log_file_path, new FileUtils.FileCallBack<List<String>>() {
            @Override
            public void onCallBack(List<String> data) {
                //汇报服务端，添加一条log信息
                 DtNetWorkController.newInstance().add_log(getAppId(context),getUserId(context),data);

            }

            @Override
            public void onFail(Exception e) {
                Log.e("----->" + "DtDataManager", "onFail:" + e.toString());
            }
        });
    }

    /**
     * 更新用户id
     * @param context
     * @param userId
     */
     void updateUserId(Context context, String userId){
        if(userId!=null){
            //保存用户id
            SPUtils.put(context,"userId",userId);
        }
    }

    /**
     * 更新开发者id
     * @param context
     * @param appId
     */
     void updateAppId(Context context, String appId){
        if(appId!=null){
            //保存开发者id
            SPUtils.put(context,"appId",appId);
        }
    }

    /**
     * 获取用户id
     * @param context
     * @return
     */
    String getUserId(Context context){

       return String.valueOf(SPUtils.get(context, "userId", ""));
    }

    /**
     * 获取开发者id
     * @param context
     * @return
     */
    String getAppId(Context context){

        return String.valueOf(SPUtils.get(context,"appId",""));
    }


}


