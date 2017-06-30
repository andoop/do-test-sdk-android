package com.dtmob.android.network;

/**
 * Created by domob on 2017/6/29.
 */

public class Api {

    //localhost:192.168.11.62
   private static final String base="http://192.168.129.1:8080";

    /**
     * 添加日志接口
     * http://192.168.11.62/log/add/add?user_id=123&log_time=66666&log_json=json_text_encode
     */
    public static final String add_log=base+"/log/add";


}
