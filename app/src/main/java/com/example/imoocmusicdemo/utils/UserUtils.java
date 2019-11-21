package com.example.imoocmusicdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.activities.LoginActivity;

public class UserUtils {
    /**
     * 验证用户输入合法性
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "手机号无效", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context)
    {
        Intent intent =new Intent(context, LoginActivity.class);
//添加intent标志 清除task栈 再新建一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//定义跳转动画 且必在startActivity后执行
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }
}
