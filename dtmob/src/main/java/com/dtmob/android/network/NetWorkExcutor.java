package com.dtmob.android.network;

import com.dtmob.android.network.interfaces.IGetRequest;
import com.dtmob.android.network.interfaces.IPostRequest;
import com.dtmob.android.network.worker.UrlConnectionNetWorker;

/**
 * Created by andoop on 2016/11/1.
 */
public class NetWorkExcutor {
    private static NetWorkExcutor INSTANCE;
    private WorkHouse workHouse;

    public static NetWorkExcutor getInstance() {
        if(INSTANCE==null){
            synchronized (NetWorkExcutor.class){
                if(INSTANCE==null){
                    INSTANCE=new NetWorkExcutor();
                }
            }
        }
        return INSTANCE;
    }

    private NetWorkExcutor() {
        workHouse=new WorkHouse();
    }

    /**
     * 执行post请求
     * @param postRequest
     */
    public void excutePost(IPostRequest postRequest){
        UrlConnectionNetWorker urlConnectionNetWorker = new UrlConnectionNetWorker();
        urlConnectionNetWorker.postRequest(postRequest);
        workHouse.add(urlConnectionNetWorker);
    }

    /**
     * 执行get请求
     * @param getRequest
     */
    public void excuteGet(IGetRequest getRequest){
        UrlConnectionNetWorker urlConnectionNetWorker = new UrlConnectionNetWorker();
        urlConnectionNetWorker.getRequest(getRequest);
        workHouse.add(urlConnectionNetWorker);
    }


}
