package com.example.imoocmusicdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class WEqualsHImageView extends AppCompatImageView {
    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

//      获取宽度
//        int width=MeasureSpec.getSize(widthMeasureSpec);
//      获取view模式 match_parent wrap_content 具体dp
//        int mode =MeasureSpec.getMode(widthMeasureSpec)
//      获取高度
//        int height=MeasureSpec.getSize(heightMeasureSpec);
    }
}
