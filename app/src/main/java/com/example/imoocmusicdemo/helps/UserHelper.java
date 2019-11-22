package com.example.imoocmusicdemo.helps;

/**
 * 1.用户登录
 *  1.当用户登陆时，利用SharedPreference保存用户登陆标记(手机号码)
 *  2.利用全局单例类UserHelper保存用户登录信息
 *      1.用户登录之后，
 *      2.用户打开应用程序，检测SHarePreference是否存在用户登录标记，
 *      如果存在则对UserHelp进行赋值，并进入主页，如果不存在，则进入登陆页面
 * 2.用户退出
 *  1.删除SHaredPreferences保存的用户标记，退到登录页面
 */
public class UserHelper {
    /**
     * 单例模式
     */
    private static UserHelper instance;

    private UserHelper() {
    }

    public static UserHelper getInstance() {
        if (instance == null) {
            synchronized (UserHelper.class) {
                if (instance == null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
