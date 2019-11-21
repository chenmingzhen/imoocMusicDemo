package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {


    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initView();
    }

    private void initView() {
        initNavBar(true,"专辑列表",false);
        mRvList=fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL)); 
        mAdapter=new MusicListAdapter(this,null);
        mRvList.setAdapter(mAdapter);
    }
}
