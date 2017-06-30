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

    //是否开启Log统计
    private boolean enable=true;

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

    public void setEnable(boolean enable){
        this.enable=enable;
    }

    public void init(Context context){
        init(context,null);
    }

    /**
     * 初始化
     */
    public void init(Context context,String appId) {
       init(context,appId,null);
    }



    public void init(Context context,String appId,String userId){
        if(!enable){
            return;
        }
       updateAppId(context,appId);
       updateUserId(context,userId);
        //清除log
        LgsTool.clear();
        //注册unCatchExceptionHandler
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new DtUnCatchExceptionHandler(context,defaultUncaughtExceptionHandler));
        //开启服务
        Log.e("----->" + "DtMob", "init:" + "开启服务");
        Intent intent=new Intent(context,DtMobService.class);
        context.startService(intent);
    }

    /**
     * 更新用户id
     * @param context
     * @param userId
     */
    public void updateUserId(Context context,String userId){
       DtDataManager.newInstance().updateUserId(context,userId);
    }

    /**
     * 更新开发者id
     * @param context
     * @param appId
     */
    public void updateAppId(Context context, String appId){
       DtDataManager.newInstance().updateAppId(context,appId);
    }

    /**
     * 退出，这里会统计所有log到本地文件
     */
    public void onExit(Context context) {
        if(!enable){
            return;
        }
        Intent intent=new Intent(context,DtMobService.class);
        intent.putExtra("command",DtMobService.SAVE_LOGS);
        context.startService(intent);
    }

}
