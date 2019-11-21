package com.example.imoocmusicdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.imoocmusicdemo.R;
import com.example.imoocmusicdemo.activities.PlayMusicActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalcaulationRvHeight;

    public MusicListAdapter(Context mContext,RecyclerView mRv) {
        this.mContext = mContext;
        this.mRv=mRv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView=LayoutInflater.from(mContext).inflate(R.layout.item_list_music,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
        Glide.with(mContext)
                .load("http://res.lgdsunday.club/poster-1.png")
                .into(holder.ivIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, PlayMusicActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 1.获取ItemView的高度
     * 2.itemView的数量
     * 3.高度*数量=RecyclerView的高度
     */
     private void setRecyclerViewHeight(){

         if(isCalcaulationRvHeight||mRv==null) return;
         isCalcaulationRvHeight=true;


//       获取ItemView的高度
         RecyclerView.LayoutParams itemViewLp=(RecyclerView.LayoutParams)mItemView.getLayoutParams();
//         itemView的数量
         int itemCount=getItemCount();
//        RecyclerView的高度
         int recyclerViewHeight=itemViewLp.height*itemCount;
//       设置RecyclerView的高度
         LinearLayout.LayoutParams rvLp=(LinearLayout.LayoutParams) mRv.getLayoutParams();
         rvLp.height=recyclerViewHeight;
         mRv.setLayoutParams(rvLp);

     }


    class ViewHolder extends RecyclerView.ViewHolder {

         View itemView;
         ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.iv_icon);
            this.itemView=itemView;
        }
    }

}
