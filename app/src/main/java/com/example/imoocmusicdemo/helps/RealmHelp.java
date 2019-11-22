package com.example.imoocmusicdemo.helps;

import com.example.imoocmusicdemo.Models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm mRealm;

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();
    }

    //关闭数据库
    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /**
     * 保存用户信息
     */
    public void saveUser(UserModel userModel) {
        //开启事务
        mRealm.beginTransaction();
        //提交到缓存
        mRealm.insert(userModel);
        //mRealm.insertOrUpdate(userModel);
        //提交
        mRealm.commitTransaction();
    }

    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> result = query.findAll();
        return result;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> realmQuery = mRealm.where(UserModel.class);
        realmQuery = realmQuery.equalTo("phone", phone).equalTo("password", password);
        UserModel userModel = realmQuery.findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;
    }

    /**
     * 获取当前用户
     */
    public UserModel getUser(){
       RealmQuery<UserModel> query= mRealm.where(UserModel.class);
       UserModel userModel=query.equalTo("phone",UserHelper.getInstance().getPhone()).findFirst();
       return userModel;
    }
    /**
     * 修改密码
     */
    public  void changePassword(String password){
         UserModel userModel=getUser();
        /**
         * 开启Realm的事务
         */
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }
}
