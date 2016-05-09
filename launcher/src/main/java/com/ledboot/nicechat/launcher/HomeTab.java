package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public abstract class HomeTab extends LinearLayout {

    private Context mContext;

    private int mSreenHeight;
    private int mSreenWidth;
    private float mScaleHeight;
    private float mScaleWidth;
    private float mScale;
    private int mTitleHeight;

    private FrameLayout mTitleView;
    private FrameLayout mContextView;

    private boolean mIsTitleVisible;


    public HomeTab(Context context) {
        super(context);
        mContext = context;
        init();
    }


    private void init() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mSreenHeight = metrics.heightPixels;
        mSreenWidth = metrics.widthPixels;
        mScaleHeight = ((float) mSreenHeight) / ((float) MainTab.S_HEIGHT);
        mScaleWidth = ((float) mSreenWidth) / ((float) MainTab.S_WIDTH);
        mScale = mScaleHeight < mScaleWidth ? mScaleWidth : mScaleHeight;

        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.TRANSPARENT);

        if (mTitleView == null) {
            mTitleView = new FrameLayout(mContext);
            if(mIsTitleVisible){
                mTitleHeight = (int)(mScale * MainTab.S_TITLE_HEIGHT);
            }else{
                mTitleHeight = 0;
            }
            mTitleView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,mTitleHeight));
//            mTitleView.setBackgroundColor(Color.rgb(0, 179, 239));
        }

        addView(mTitleView);

        if(mContextView == null){
            mContextView = new FrameLayout(mContext);
            mContextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
            mContextView.setBackgroundColor(Color.TRANSPARENT);

        }

    }

    protected abstract void onCreate(Bundle savedInstanceState);

    protected abstract View onCreateTitleView(ViewGroup parent, LayoutInflater inflater);

    protected abstract View onCreateContentView(ViewGroup parent, LayoutInflater inflater);

    protected abstract void recycle();
}
