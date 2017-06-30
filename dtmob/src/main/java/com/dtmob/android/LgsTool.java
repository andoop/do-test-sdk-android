package com.dtmob.android;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.view.ViewOutlineProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2017/6/26
* explain：log收集工具
* * * * * * * * * * * * * * * * * * */
class LgsTool {

    /**
     * 清除log
     */
    static void clear() {
        //清除缓存的log
        ArrayList<String> commandLine = new ArrayList<>();
        commandLine.add("logcat");
        commandLine.add("-c");
        try {
            Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存log
     */
    static void save(final Context context) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    DtDataManager.newInstance().init_Log_File();
                    ArrayList<String> commandLine = new ArrayList<>();
                    commandLine.add("logcat");
                    commandLine.add("-d");//使用该参数可以让logcat获取日志完毕后终止进程
                    commandLine.add("-v");
                    commandLine.add("time");
                    commandLine.add("-f");//如果使用commandLine.add(">");是不会写入文件，必须使用-f的方式
                    commandLine.add(DtDataManager.newInstance().get_log_file_path());
                    Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //预留log保存时间
                SystemClock.sleep(1500);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                DtDataManager.newInstance().do_rep_local_logs(context);

            }
        }.execute();

    }

}
