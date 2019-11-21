package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initNavBar(true,"个人中心",false);
    }

    /*
    * 修改密码点击事件
    * */
    public void onChangeClick(View view) {
        startActivity(new Intent(MeActivity.this,ChangePasswordActivity.class));
    }

    /*
    * 退出登录*/
    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
