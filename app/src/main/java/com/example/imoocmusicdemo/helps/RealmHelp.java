package com.example.imoocmusicdemo.helps;

import android.content.Context;
import android.text.format.DateUtils;

import com.example.imoocmusicdemo.Models.AlbumModel;
import com.example.imoocmusicdemo.Models.MusicModel;
import com.example.imoocmusicdemo.Models.MusicSourceModel;
import com.example.imoocmusicdemo.Models.UserModel;
import com.example.imoocmusicdemo.migration.Migration;
import com.example.imoocmusicdemo.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm mRealm;

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();
    }


    /**
     * Realm 发生结构性变化（模型或者模型中的字段新增，修改，删除）的时候，我们需要对数据库进行迁移
     */
    private static RealmConfiguration getRealConf()
    {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                /*.deleteRealmIfMigrationNeeded()*/
                .migration(new Migration())
                .build();
    }

    /**
     * 告诉Realm数据需要迁移，并且为Realm设置最新的配置
     */
    public static void migration(){
        //Realm设置最新的配置
        RealmConfiguration conf=getRealConf();
        //告诉Realm数据需要迁移
        Realm.setDefaultConfiguration(conf);
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    /**
     * 1.用户登录 存放数据
     * 2.用户退出删除数据
     */
    /**
     * 保存音乐源数据
     */
    public void setMusicSource(Context context){
        //拿到存放的资源文件中的数据
        String musicSourceJson=DataUtils.getJsonFromAssets(context,"DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,musicSourceJson);
        mRealm.commitTransaction();
    }
    /**
     * 删除音乐源数据
     * 1.RealmResult delete
     * Realm delete 删除模型下所有的模型
     */
    public  void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.commitTransaction();
    }
    /**
     * 返回音乐源数据
     */
    public MusicSourceModel getMusicSource(){
        return mRealm.where(MusicSourceModel.class).findFirst();
    }
    /**
     * 返回歌单
     */
    public AlbumModel getAlbum (String albumId) {
        return mRealm.where(AlbumModel.class).equalTo("albumId", albumId).findFirst();
    }
    /**
     * 返回音乐
     */
    public MusicModel getMusic(String musicId){
        return mRealm.where(MusicModel.class).equalTo("musicId",musicId).findFirst();
    }

}
