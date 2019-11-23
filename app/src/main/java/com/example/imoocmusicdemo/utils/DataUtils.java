package com.example.imoocmusicdemo.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataUtils {
    /**
     * 读取资源文件数据
     * @return
     */
    public static String getJsonFromAssets(Context context,String fileName){
        /**
         * 1.StringBuilder存放读出的数据
         * 2.AssetManager 资源管理器 Open方法 打开指定资源文件,返回inputStream 输入流
         * 3.inputStreamReader(字节到字符的桥接器),BufferedReader(存放读取字符的缓冲区)
         * 4.循环利用BufferedReader的readLine方法读取每一行数据，
         * 并且把读取的数据放入到StringBuilder里面
         * 5.返回读取出来的所有数据
          */
        StringBuilder stringBuilder=new StringBuilder();
        //AssetManager 资源管理器
        AssetManager assetManager=context.getAssets();
        //Open方法 打开指定资源文件,返回inputStream 输入流
        try {
            InputStream inputStream=assetManager.open(fileName);
            //inputStreamReader(字节到字符的桥接器),BufferedReader(存放读取字符的缓冲区)
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
