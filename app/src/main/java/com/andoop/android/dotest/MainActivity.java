package com.andoop.android.dotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dtmob.android.DtMob;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("-----#" + "MainActivity", "onCreate:" + "111hhhhhhhhhhhhhhhhhhh");
        Log.e("-----#" + "MainActivity", "onCreate:" + "gggggggggggggggggggg");
        Log.e("-----#" + "MainActivity", "onCreate:" + "hhhhhhjjjjjjjjjjjjjjjjjj");
        Log.e("-----#" + "MainActivity", "onCreate:" + "ooooooooooooooooooo");
        throw new NullPointerException("dddddddddd");
    }

    @Override
    protected void onDestroy() {
        DtMob.newInstance().onExit();
        super.onDestroy();
    }
}
