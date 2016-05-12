package com.ledboot.nicechat.launcher;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ledboot.nicechat.core.Debuger;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public abstract class HomeTab extends LinearLayout {

    public static final java.lang.String TAG = HomeTab.class.getSimpleName();
    private Context mContext;

    private int mSreenHeight;
    private int mSreenWidth;
    private float mScaleHeight;
    private float mScaleWidth;
    private float mScale;
    private int mTitleHeight;

    private FrameLayout mTitleStub = null;
    private FrameLayout mContentStub = null;

    private View mTitleView = null;
    private View mContextView = null;

    private boolean mIsTitleVisible;

    protected int mNorResId;
    protected int mSelResId;
    protected String mLabel = "";

    private boolean mIsPreparing =false;
    private boolean mIsPrepared =false;

    private LayoutInflater mInflater = null;



    public HomeTab(Context context) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        init();
    }


    private void init() {


        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.TRANSPARENT);

        if (mTitleStub == null) {
            mTitleStub = new FrameLayout(mContext);
            if(mIsTitleVisible){
                mTitleHeight = (int)(MainPanel.S_SCALE * MainPanel.S_TITLE_HEIGHT);
            }else{
                mTitleHeight = 0;
            }
            mTitleStub.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,mTitleHeight));
//            mTitleStub.setBackgroundColor(Color.rgb(0, 179, 239));
        }

        addView(mTitleStub);

        if(mContentStub == null){
            mContentStub = new FrameLayout(mContext);
            mContentStub.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
            mContentStub.setBackgroundColor(Color.TRANSPARENT);
        }
        addView(mContentStub);

    }

    void prepareToShow(){
        Debuger.logD(TAG,"HomeTab prepareToShow");
        if(mIsPrepared || mIsPrepared){
            return;
        }
        mIsPreparing = true;
        if(mTitleView == null){
            mTitleView = onCreateTitleView(mTitleStub,mInflater);
            if(mTitleView != null){
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                mTitleView.setLayoutParams(params);
                mTitleStub.addView(mTitleView);
            }
        }

        if(mContextView == null){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mContextView = onCreateContentView(mContentStub,mInflater);
                    if(mContextView != null){
                        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                        params.gravity = Gravity.CENTER;
                        mContextView.setLayoutParams(params);
                        mContentStub.addView(mContextView);
                    }
                    mIsPrepared = true;
                    mIsPreparing = false;
                }
            });
        }

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
