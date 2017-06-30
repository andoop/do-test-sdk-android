package com.andoop.android.dotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dtmob.android.DtMob;

import java.sql.Blob;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //开启日志统计
        DtMob.newInstance().setEnable(true);
        DtMob.newInstance().init(this,"100001","user_id");

        setContentView(R.layout.activity_main);

        Log.e("-----#" + "MainActivity", "onCreate:" + "888888888888");
        Log.e("-----#" + "MainActivity", "onCreate:" + "999999999");
        Log.e("-----#" + "MainActivity", "onCreate:" + "hhhhhhjjjjjjjjjjjjjjjjjj");
        Log.e("-----#" + "MainActivity", "onCreate:" + "ooooooooooooooooooo");

       // throw new NullPointerException("dddddddddd");
    }

    @Override
    protected void onDestroy() {
        DtMob.newInstance().onExit(this);
        super.onDestroy();
    }
}
