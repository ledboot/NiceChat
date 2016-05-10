package com.ledboot.nicechat.launcher;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wengaowei728 on 16/5/9.
 */
public class MainPanel extends FrameLayout {

    private Context mContext;
    private Activity mAttached;

    public static final int S_WIDTH = 1080;
    public static final int S_HEIGHT = 1920;


    /**
     * 头部高度
     */
    public static final int S_TITLE_HEIGHT = 150;
    /**
     * 底部高度
     */
    public static final int S_BOTTOM_PANEL_HEIGHT = 300;

    public static final String TAG_SESSION = "_session";
    public static final String TAG_EXPLORE = "_explore";
    public static final String TAG_FUN = "_fun";
    public static final String TAG_CIRCLE = "_circle";
    public static final String TAG_SETTING = "_setting";



    private TabContainer mTabContainer;
    private LinearLayout mBottomPanel;

    private List<HomeTab> mTabs = null;
    private List<TabItem> mBottomTabs = null;
    public static float S_SCALE;

    public MainPanel(Activity attached) {
        super(attached);
        mContext = attached;
        mAttached = attached;

//        mTabs = genTabs();
        init();
        initView();
    }

    private void initView(){
        LinearLayout root = new LinearLayout(mContext);
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        root.setLayoutParams(rootParams);
        root.setOrientation(LinearLayout.VERTICAL);

        mTabs = genTabs();

        mTabContainer = new TabContainer(mContext,this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1.0f;
        mTabContainer.setLayoutParams(layoutParams);
        root.addView(mTabContainer);

        mBottomPanel = new LinearLayout(mContext);
        mBottomPanel.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bottomPanelParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomPanel.setLayoutParams(bottomPanelParams);

        mBottomTabs = new ArrayList<>();

        for(int i = 0; i < mTabs.size();i++){
            HomeTab tab = mTabs.get(i);
            TabItem item = new TabItem(mContext,tab.getNorResId(),tab.getLabel());
            LinearLayout.LayoutParams bottomTabParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            bottomTabParams.weight = 1.0f;
            tab.setLayoutParams(bottomTabParams);
            mBottomTabs.add(item);
            mBottomPanel.addView(item);
        }
    }

    private void init(){
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int mSreenHeight = metrics.heightPixels;
        int mSreenWidth = metrics.widthPixels;
        float mScaleHeight = ((float) mSreenHeight) / ((float) S_HEIGHT);
        float mScaleWidth = ((float) mSreenWidth) / ((float) S_WIDTH);
        S_SCALE = mScaleHeight < mScaleWidth ? mScaleWidth : mScaleHeight;
    }

    private List<HomeTab> genTabs(){
        ArrayList<HomeTab> tabs = new ArrayList<>();
        tabs.add(new SessionTab(mContext));
        tabs.add(new ExploreTab(mContext));
        tabs.add(new FunTab(mContext));
        tabs.add(new CircleTab(mContext));
        tabs.add(new SettingTab(mContext));
        return tabs;
    }

    public List<HomeTab> getTabs(){
        return mTabs;
    }
}
