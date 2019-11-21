package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.imoocmusicdemo.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initNavBar(true,"注册",false);
    }


    public void onRegisterClick(View view) {

    }
}
