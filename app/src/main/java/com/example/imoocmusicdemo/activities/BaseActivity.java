package com.example.imoocmusicdemo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imoocmusicdemo.R;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ImageView mIvBack,mIvMe;
    private TextView mTvTitle;

    /**
     * findViewById
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View>T fd(@IdRes int id)
    {
        return findViewById(id);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*hide bar*/
        getSupportActionBar().hide();


    }

    /**
     *
     * @param isShowBack 是否显示返回键
     * @param title      nav栏标题内容
     * @param isShowMe    是否显示个人中心
     */
    protected void initNavBar(boolean isShowBack,String title,boolean isShowMe)
    {
        mIvBack=fd(R.id.iv_back);
        mTvTitle=fd(R.id.tv_title);
        mIvMe=fd(R.id.iv_me);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE:View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE:View.GONE);
        mTvTitle.setText(title);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                finish();
            }
        });

        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
        
    }
}
