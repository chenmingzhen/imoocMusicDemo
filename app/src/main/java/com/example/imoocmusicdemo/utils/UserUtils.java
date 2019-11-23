package com.example.imoocmusicdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.imoocmusicdemo.Models.UserModel;
import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.activities.LoginActivity;
import com.example.imoocmusicdemo.helps.RealmHelp;
import com.example.imoocmusicdemo.helps.UserHelper;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

public class UserUtils {
    /**
     * 验证用户输入合法性
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            //Toast.makeText(context, "手机号无效", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "手机号无效", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            //Toast.makeText(context, "密码为空", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "密码为空", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }

        /**
         *1.用户当前手机号是否被注册了
         * 2.用户输入的手机号与密码是否匹配
         *
         */
        if (!UserUtils.userExistFromPhone(phone)) {
            //Toast.makeText(context, "当前手机号未注册", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "当前手机号未注册", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }
        RealmHelp realmHelp = new RealmHelp();
        boolean result = realmHelp.validateUser(phone, EncryptUtils.encryptMD5ToString(password));


        if (!result) {
            //Toast.makeText(context, "手机号或密码不正确", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "手机号或密码不正确", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }

        //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context, phone);
        if (!isSave) {
            TastyToast.makeText(context, "系统错误，请稍后重试", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }

        //保存用户标记在全局单例类中
        UserHelper.getInstance().setPhone(phone);

        //保存音乐源数据
        realmHelp.setMusicSource(context);
        realmHelp.close();
        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context) {

        //删除sp保存的用户标志
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove) {
            TastyToast.makeText(context, "系统错误，请稍后重试", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return;
        }
        //删除数据源
        RealmHelp realmHelp=new RealmHelp();
        realmHelp.removeMusicSource();
        realmHelp.close();

        Intent intent = new Intent(context, LoginActivity.class);
//添加intent标志 清除task栈 再新建一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//定义跳转动画 且必在startActivity后执行
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    /**
     * 注册用户
     *
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context, String phone, String password, String passwordConfirm) {
        if (!RegexUtils.isMobileExact(phone)) {
            //Toast.makeText(context, "手机号无效", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "手机号无效", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }
        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            //Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "请确认密码", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }

        //当前输入的手机号是否被注册
        /**
         *1.通过Realm获取当前已经被注册的所有用户
         * 2.根据用户输入的手机号匹配查询的所有用户，如果可以匹配则证明手机号被注册了
         */
        if (UserUtils.userExistFromPhone(phone)) {
            //Toast.makeText(context, "手机号已存在", Toast.LENGTH_SHORT).show();
            TastyToast.makeText(context, "手机号已存在", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }


        UserModel userModel = new UserModel();
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        userModel.setPhone(phone);
        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户数据
     *
     * @param userModel
     */
    public static void saveUser(UserModel userModel) {
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

    /**
     * 根据手机号判断用户是否存在
     */
    public static boolean userExistFromPhone(String phone) {
        boolean result = false;

        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> allUser = realmHelp.getAllUser();

        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
                //当前手机号已经存在数据库中了
                result = true;
                break;
            }
        }
        realmHelp.close();
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean validateUserLogin(Context context) {
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码
     * 1.数据验证
     *      1.原密码是否输入
     *      2.新密码是否输入并且是否是确认密码一致
     *      3.原密码是否输入正确
     *          1.Realm获取当前登录的用户模型
     *          2.根据用户模型中保存的密码匹配用户原密码匹配
     * 2.利用Realm模型自动更新特征完成密码修改
     *
     */
    public static boolean changePassword(Context context,String oldPassword,String password,String passwordConfirm){
        if(TextUtils.isEmpty(oldPassword)){
            TastyToast.makeText(context, "请输入原密码", TastyToast.LENGTH_LONG, TastyToast.INFO);
            return false;
        }
        if(TextUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            TastyToast.makeText(context, "请确认密码", TastyToast.LENGTH_LONG, TastyToast.INFO);
            return false;
        }
        /**
         * 验证密码
         */
        RealmHelp realmHelp=new RealmHelp();
        UserModel userModel=realmHelp.getUser();

        if(!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
            TastyToast.makeText(context, "原密码错误", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            return false;
        }
        realmHelp.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmHelp.close();
        return true;
    }


}
