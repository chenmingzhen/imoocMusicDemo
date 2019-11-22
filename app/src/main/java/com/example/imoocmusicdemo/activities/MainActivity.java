package com.example.imoocmusicdemo.activities;

import android.os.Bundle;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.adapters.MusicGridAdapter;
import com.example.imoocmusicdemo.adapters.MusicListAdapter;
import com.example.imoocmusicdemo.views.GridSpaceItemDecoration;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGird, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initNavBar(false, "就是音乐", true);
        mRvGird = fd(R.id.rv_grid);
        mRvGird.setLayoutManager(new GridLayoutManager(this, 3));
        //歌单分割线
        mRvGird.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGird));
//使RecyclerView不跟随滑动
        mRvGird.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(this);
        mRvGird.setAdapter(mGridAdapter);


        /**
         * 1.假如已知列表高度的情况下,可以直接在布局把RecycleView的高度定义
         * 2.不知道列表高度的情况下,可以手动计算RecyclerView的高度
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdapter(this, mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
