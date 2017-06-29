package com.dtmob.android;

import android.os.Environment;

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
    static void save() {
        File log_file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + "00");
        if (!log_file_dir.exists()) {
            try {
                log_file_dir.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File log_file = new File(log_file_dir, "logcat.txt");
        if (log_file.exists()) {
            log_file.delete();
        }
        try {
           log_file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder log = new StringBuilder();
            ArrayList<String> commandLine = new ArrayList<>();
            commandLine.add("logcat");
            commandLine.add("-d");//使用该参数可以让logcat获取日志完毕后终止进程
            commandLine.add("-v");
            commandLine.add("tag");
            commandLine.add("-f");//如果使用commandLine.add(">");是不会写入文件，必须使用-f的方式
            commandLine.add(log_file.getAbsolutePath());
            Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
