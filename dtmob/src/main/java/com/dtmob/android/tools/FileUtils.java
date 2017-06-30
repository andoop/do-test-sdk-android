package com.dtmob.android.tools;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domob on 2017/6/29.
 */

public class FileUtils {

    /**
     * 将指定的文件内容一行一行读取，并存入到list中
     * @param file_path
     * @param fileCallBack
     */
    public static void readTextFromFileOfListFormat(String file_path,final FileCallBack<List<String>> fileCallBack){
        new AsyncTask<String,Void,List<String>>(){
            @Override
            protected List<String> doInBackground(String... params) {
                //将log信息从文件中读取出来
                BufferedReader reader=null;
                List<String> logs=new ArrayList<>();
                try{
                    reader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(params[0]))));
                    String line="";
                    while (line!=null){
                        line=reader.readLine();
                        Log.e("----->" + "FileUtils", "doInBackground:" + line);
                        logs.add(line);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    if(fileCallBack!=null)
                        fileCallBack.onFail(e);
                }finally {
                    //关闭流
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            if(fileCallBack!=null)
                                fileCallBack.onFail(e);
                        }
                    }
                }
                return logs;
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                if(fileCallBack!=null)
                    fileCallBack.onCallBack(strings);

            }
        }.execute(file_path);
    }


    /**
     * 初始化文件，删除旧的，创建新的
     * @param file_path
     */
   public static void init_File(String file_path) {
        if (TextUtils.isEmpty(file_path))
            return;

        File file = new File(file_path);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface FileCallBack<T>{
        void onCallBack(T data);
        void onFail(Exception e);
    }
}
