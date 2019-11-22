package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.helps.RealmHelp;
import com.example.imoocmusicdemo.utils.UserUtils;
import com.example.imoocmusicdemo.views.inputView;

public class RegisterActivity extends BaseActivity {

    private inputView mInputPhone, mInputPassword, mInputPasswordconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true, "注册", false);
        mInputPhone = fd(R.id.input_phone_1);
        mInputPassword = fd(R.id.input_password_1);
        mInputPasswordconfirm = fd(R.id.input_password_confirm);
    }


    /**
     * 注册按钮点击事件
     * 1.输入内容合法性验证
     * 1.用户手机号
     * 2.是否输入密码，确认密码，两次密码是否一样
     * 3.输入的手机号是否被注册了
     * 2.保存用户输入的手机号和密码(MD5加密密码)
     *
     * @param view
     */
    public void onRegisterClick(View view) {
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordconfirm.getInputStr();
        Boolean result = UserUtils.registerUser(this, phone, password, passwordConfirm);

        //
        if (!result) return;
        //后退页面
        onBackPressed();
    }
}
