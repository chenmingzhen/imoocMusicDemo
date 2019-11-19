package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.imoocmusicdemo.R;

import java.util.Timer;
import java.util.TimerTask;


//1.延迟3秒
//2.跳转页面
public class WelcomeActivity extends BaseActivity {
    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();

    }

    /*init*/
    private void init() {
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("WelcomeActivity", "run: "+Thread.currentThread());
                toLogin();
            }
        },3*1000);
    }

    private void toLogin() {
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
