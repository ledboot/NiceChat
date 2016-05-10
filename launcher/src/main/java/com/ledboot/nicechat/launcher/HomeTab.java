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

    protected int mNorResId;
    protected int mSelResId;
    protected String mLabel = "";


    public HomeTab(Context context) {
        super(context);
        mContext = context;
        init();
    }


    private void init() {


        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.TRANSPARENT);

        if (mTitleView == null) {
            mTitleView = new FrameLayout(mContext);
            if(mIsTitleVisible){
                mTitleHeight = (int)(MainPanel.S_SCALE * MainPanel.S_TITLE_HEIGHT);
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
        addView(mContextView);

    }

    public int getNorResId(){
        return mNorResId;
    }

    public int getSelResId(){
        return mSelResId;
    }

    public String getLabel(){
        return mLabel;
    }

    protected abstract void setFacade();

    protected abstract String getTabTag();

    protected abstract void onCreate(Bundle savedInstanceState);

    protected abstract View onCreateTitleView(ViewGroup parent, LayoutInflater inflater);

    protected abstract View onCreateContentView(ViewGroup parent, LayoutInflater inflater);

    protected abstract void recycle();
}
