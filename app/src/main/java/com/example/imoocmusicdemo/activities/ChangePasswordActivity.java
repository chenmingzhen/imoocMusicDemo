package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.utils.UserUtils;
import com.example.imoocmusicdemo.views.inputView;
import com.sdsmdg.tastytoast.TastyToast;

public class ChangePasswordActivity extends BaseActivity {

    private inputView mOldPassword,mPassword,mPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        TastyToast.makeText(getApplicationContext(),"请务必记住修改后的密码",TastyToast.LENGTH_LONG,TastyToast.WARNING);
        initNavBar(true, "修改密码", false);
        mOldPassword=fd(R.id.input_old_password);
        mPassword=fd(R.id.input_password);
        mPasswordConfirm=fd(R.id.input_password_confirm);
    }

    public void onChangePassword(View view) {
        String oldPassword=mOldPassword.getInputStr();
        String password=mPassword.getInputStr();
        String passwordConfirm=mPasswordConfirm.getInputStr();
        boolean result=UserUtils.changePassword(this,oldPassword,password,passwordConfirm);
        if(!result){
            return;
        }
        TastyToast.makeText(getApplicationContext(),"修改密码成功",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
        /**
         * 退出登录
         */
        UserUtils.logout(this);
    }
}
