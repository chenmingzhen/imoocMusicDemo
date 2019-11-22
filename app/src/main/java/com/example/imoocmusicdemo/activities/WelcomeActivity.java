package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.utils.UserUtils;
import com.sdsmdg.tastytoast.TastyToast;

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
        final boolean isLogin=UserUtils.validateUserLogin(this);
        mTimer = new Timer();
        TastyToast.makeText(getApplicationContext(), "欢迎使用就是音乐", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isLogin){
                    toMain();
                }else{
                    toLogin();
                }
            }
        }, 3 * 1000);
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
