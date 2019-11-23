package com.example.imoocmusicdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.imoocmusicdemo.Models.AlbumModel;
import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.adapters.MusicListAdapter;
import com.example.imoocmusicdemo.helps.RealmHelp;

public class AlbumListActivity extends BaseActivity {


    private RecyclerView mRvList;
    private MusicListAdapter mAdapter;
    public static final String ALBUM_ID="albumId";
    private String mAlbumId;
    private RealmHelp mRealmHelp;
    private AlbumModel mAlbumModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        iniData();
        initView();
    }

    private void iniData(){
        mAlbumId=getIntent().getStringExtra(ALBUM_ID);
        mRealmHelp=new RealmHelp();
        mAlbumModel=mRealmHelp.getAlbum(mAlbumId);
    }

    private void initView() {
        initNavBar(true, "专辑列表", false);
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new MusicListAdapter(this, null,mAlbumModel.getList());
        mRvList.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelp.close();
    }
}
